package selective.repeat;



import packet.AckPacket;
import packet.WindowNode;
import utils.Serializer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Server {

	public  int serverPortNum,windowSize,seed;
    public double probability;

    public Server(int serverPortNum, int windowSize, int seed, double probability) {
		super();
		this.serverPortNum = serverPortNum;
		this.windowSize = windowSize;
		this.seed = seed;
		this.probability = probability;
	}

    public Server() {
    	super();
		this.serverPortNum = 8793;
		this.windowSize = 10;
		this.seed = 100;
		this.probability = 0.01;
    }
    public void run() throws Exception{
    	 int i=0;

         DatagramSocket serverSocket = new DatagramSocket(serverPortNum);
         System.out.println("serveur demare a la port "+serverPortNum);
         byte[] receiveData = new byte[3024];
       
         while (true) {

             DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
             serverSocket.receive(receivePacket);
  
             CongestionControl congestionControl = new CongestionControl();

             ConcurrentLinkedQueue<WindowNode> window = new ConcurrentLinkedQueue<>();
             DatagramSocket socket = new DatagramSocket();
             Test stop = new Test();
             stop.flag = false;
             ThreadSender thrsender=new ThreadSender(receivePacket, window, windowSize, socket, stop, congestionControl, seed, probability,i,serverPortNum);
             ThreadReceiver thrreceiver =new ThreadReceiver(receivePacket, window, windowSize, socket, stop, congestionControl,i,serverPortNum);
             thrsender.start();
             thrreceiver.start();
            i++;
           

         }
         
        
    }


}
