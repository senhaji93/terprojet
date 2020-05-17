package selective.repeat;

import packet.AckPacket;
import packet.WindowNode;
import utils.Serializer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


public class ThreadReceiver extends Thread {

	private ConcurrentLinkedQueue<WindowNode> window;
	private int maxWindowSize;
	private int portno;
	private DatagramPacket packet;
	private int iii=0;
	private int iack;
	private int PortNum;
   
	private Test done;
	private DatagramSocket childReceiverServerSocket;

	private CongestionControl congestionControl;

	public ThreadReceiver(DatagramPacket receivePacket,
						  ConcurrentLinkedQueue<WindowNode> window, int windowSize,
						  DatagramSocket socket, Test stop, CongestionControl congestionControl,int iack,int PortNum) {
		this.window = window;
		this.maxWindowSize = windowSize;
		this.childReceiverServerSocket = socket;
		this.packet = receivePacket;
		this.done = stop;
		this.iack=iack;
		this.PortNum=PortNum;
        this.congestionControl = congestionControl;
	}

	@Override
	public void run() {
		iack++;
		 FileWriter myWriter = null;
		    int port = packet.getPort();
		try {
			
			myWriter = new FileWriter( "C:\\Users\\PC\\Desktop\\ack"+PortNum+"."+port+iack+".txt");
			                         
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 FileWriter myWriteri = null;
		    
		try {
			
			myWriteri = new FileWriter( "C:\\Users\\PC\\Desktop\\ser"+PortNum+".txt");
			
			                         
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		byte[] receiveAck = new byte[3048];
		try {
			 myWriteri.write("" + iack);
			 myWriteri.close();
			while (!done.flag || !window.isEmpty()) {

				DatagramPacket receivePacket = new DatagramPacket(receiveAck,
						receiveAck.length);
			
				
               // System.out.println("Receiving Acknowledgement...");

                childReceiverServerSocket.receive(receivePacket);

				AckPacket ackPacket = (AckPacket) Serializer
						.deserialize(receivePacket.getData());
				// get the Ack no from the data Packet
				int ackno = ackPacket.getAckno();
			  
			       
			        myWriter.write("\nack"+ ackno);
			       
				
				//System.out.println("Ack. number = " + ackno);
				

				synchronized(this) {
					// get the Seqno of the Latest packet in the window. which
					// is at index Zero
					int base = window.peek().getSeqno();
					int index = (ackno - base) / 500;

					// Ack must be greater than the base
              
					if (base <= ackno) {
						// stop the timer of the required packet
						Iterator<WindowNode> it = window.iterator();
						for (int i = 0; i < index; i++) {
							it.next();
						}
						
                        WindowNode curNode = it.next();

                        if (index > 0) { // duplicate ack!

                            if (congestionControl.getState() == CongestionControl.FAST_RECOVERY) {
                                congestionControl.setCwnd(congestionControl
                                        .getCwnd() + 1);
                            } else {
                                congestionControl.updateAckCount();
                                if (congestionControl.getAckCount() == 3) {
                                    congestionControl.moveToFastRecovery();
                                }
                            }

                        } else { // new ack. for BASE!

                            if (congestionControl.getState() == CongestionControl.SLOW_START) {

                                congestionControl.setCwnd(congestionControl
                                        .getCwnd() + 1);
                                congestionControl.setAckCount(0);

                            } else if (congestionControl.getState() == CongestionControl.CONGESTION_AVOIDANCE) {
                                congestionControl.updateAvoidanceNewAck();
                            } else {
                                congestionControl.moveToCongestionAvoidance();
                            }

                        }

                        if (congestionControl.getState() == CongestionControl.SLOW_START
                                && congestionControl.getCwnd() >= congestionControl
                                .getSsthreshold()) {
                            congestionControl.moveToCongestionAvoidance();
                        }

						curNode.ackReceived();

						// loop over the window, and remove all the Ack packet
						while (!window.isEmpty() && window.peek().isAck()) {
							// watch out here some error might occur
							window.poll();
						}

						System.out.println("Current window size = " + window.size());
						// if(window.isEmpty()){
						// System.out.println("empty????????????????????????");
						// break;
						// }
					}
				}

			}
			 myWriter.close();
			  

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
