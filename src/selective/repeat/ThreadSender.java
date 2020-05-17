package selective.repeat;

import packet.Packet;
import packet.WindowNode;
import utils.CheckSumCalculator;
import utils.Serializer;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


public class ThreadSender extends Thread {


    private DatagramPacket packet;
    private ConcurrentLinkedQueue<WindowNode> window;
    private int maxWindowSize;
    private DatagramSocket childServerSocket;
   private int sequn;
    private CongestionControl congestionControl;
    private int PortNum;
    private int seed = 100;
    private double plp = 0.1;

    private Test done;

    public ThreadSender(DatagramPacket packet, ConcurrentLinkedQueue<WindowNode> window, int windowSize, DatagramSocket socket,
                        Test stop, CongestionControl congestionControl, int seed, double probability,int seq,int PortNum) {
        this.packet = packet;
        this.window = window;
        this.maxWindowSize = windowSize;
        this.childServerSocket = socket;
        this.done = stop;
      this.sequn=seq;
        this.congestionControl = congestionControl;

        this.seed = seed;
        this.plp = probability;
        this.PortNum=PortNum;
    }

    @Override
    public void run() {
    	sequn++;
        //get IPaddress and port from the packet
        InetAddress IPAddress = packet.getAddress();
        int port = packet.getPort();
        //get file name from the packet
        String fileName = "";
        try {
            Packet dataPacket = (Packet) Serializer.deserialize(packet.getData());
            fileName = new String(dataPacket.getData());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        InputStream reader = null;

        try {

            System.out.println(fileName);
            reader = new FileInputStream(fileName);
            int len = 500;
            int actuallyRead;
            int seqno = 0;
            int counterToCheckSum = 0;
            byte[] chunk = new byte[len];
            FileWriter myWriter = null;
    		try {
    			myWriter = new FileWriter("C:\\Users\\PC\\Desktop\\sen"+PortNum+"."+port+"."+sequn+".txt");
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}

            while (true) {
                if (window.size() < Math.min(maxWindowSize, congestionControl.getCwnd())) {
                    chunk = new byte[len];
                    actuallyRead = reader.read(chunk, 0, len);
                    if (actuallyRead == -1) {
                        break;
                    }

                    System.out.println("Reading from file...\n"+actuallyRead+" bytes was read");
                    Packet packet = new Packet((short) actuallyRead, seqno, chunk);

                    //checking checkSum 1
                    // packet.setCksum((short)34);

                    final byte[] toSendBytes = Serializer.serialize(packet);
                    final DatagramPacket sendPacket = new DatagramPacket(toSendBytes, toSendBytes.length, IPAddress, port);

                    WindowNode node = new WindowNode(packet, false);
                    // syncronized
                    window.add(node);
                    // synchronized

                    //checking checkSum 2
                    //  packet.setCksum(CheckSumCalculator.calculateCheckSumWithParam(packet.getLen(), packet.getSeqno(), packet.getData()));
                    //  final byte[] toSendBytes1 = Serializer.serialize(packet);
                    myWriter.write("\nsen"+ seqno);
                    scheduleTimer(node, sendPacket);
                    seqno += len;
                } else {
//                    System.out.println("Fulllllllllllllsllllllllll");
                }
            }
            myWriter.close();

            done.flag = true;

            System.out.println("Hell yo");
            // file sent; notify the client
            Packet toSendPacket = new Packet((short) 6, seqno, "ok 200".getBytes());

            WindowNode node = new WindowNode(toSendPacket, false);
            // syncronized
            window.add(node);
            // synchronized

            //checking checkSum 2
            //  packet.setCksum(CheckSumCalculator.calculateCheckSumWithParam(packet.getLen(), packet.getSeqno(), packet.getData()));
            //  final byte[] toSendBytes1 = Serializer.serialize(packet);

            byte[] toSendBytes = Serializer.serialize(toSendPacket);
            DatagramPacket sendPacket = new DatagramPacket(toSendBytes, toSendBytes.length, packet.getAddress(), packet.getPort());
            scheduleTimer(node, sendPacket);
        } catch (FileNotFoundException e) {
            Packet toSendPacket = new Packet((short) 6, 0, "no 404".getBytes());
            byte[] toSendBytes = new byte[0];
            try {
                toSendBytes = Serializer.serialize(toSendPacket);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            DatagramPacket sendPacket = new DatagramPacket(toSendBytes, toSendBytes.length, IPAddress, port);
            WindowNode node = new WindowNode(toSendPacket, false);
            // syncronized
            window.add(node);
            scheduleTimer(node, sendPacket);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
//            if (childServerSocket != null) {
//                childServerSocket.close();
//            }
        }


    }

    public void doAction(final DatagramPacket datagramPacket, int cnt) {
        try {
            if(cnt==0) {
             //   System.out.println("Sending next packet...");
            }else{
               // System.out.println("Timeout!...Resending packet...");
            }
            childServerSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void scheduleTimer(WindowNode node, DatagramPacket sendPacket) {
        node.getTimer().scheduleAtFixedRate(new TimerTask() {
            Random rand = new Random();
            boolean first = false;
            int cnt = 0;

            @Override
            public void run() {

                if (first) {
                    synchronized (this) {
                        congestionControl.moveToSlowStart();
                    }
                }

                int randNum = rand.nextInt(seed);
//                System.out.println(new String(sendPacket.getData()));
                int x = (int) (plp * seed);
                if (randNum >= x) {
                    doAction(sendPacket, cnt);
                } // else -> loss occur
                else {
                    System.out.println("Loss!!");
                }

                first = true;
                cnt++;
                //checking checkSum last
                // return the packet to the correct value
                //   sendPacket.setData(toSendBytes1);
                //  sendPacket.setLength(toSendBytes1.length);

            }

        }, 0, 300);
    }


}
