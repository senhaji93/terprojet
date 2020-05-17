package selective.repeat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Apelclient {

	
	public static void main(String[] args) throws Exception {
		 BufferedReader k = new BufferedReader(new FileReader("client.in"));
		   String serverIp = k.readLine();
	        int serverPortNum = Integer.parseInt(k.readLine());
	        int clientPortNum = Integer.parseInt(k.readLine());
	        String requestFile = k.readLine();
	        String outputFile = k.readLine();
	        int  winSize = Integer.parseInt(k.readLine());
	        k.close();
	       
Client c1=new Client(serverIp, serverPortNum, clientPortNum, requestFile, outputFile, winSize);
c1.run();




	}

}
