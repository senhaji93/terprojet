package utils;

public class CheckSumCalculator {
	
		
	public static short calculateCheckSumWithParam(short len,int seqno,byte[] data){		
		    	
		    	byte [] extendedData=new byte[data.length+3];   	
		    	System.arraycopy( data, 0, extendedData, 0, data.length );
		        int secondHalfOfLen= (short) (seqno>>16);       
		        int firstHalfOfLen= (short) seqno;
		    	
		        extendedData[data.length]=(byte) (len&0xFFFF);      	
		        extendedData[data.length]=(byte) (secondHalfOfLen&0xFFFF);      	
		        extendedData[data.length]=(byte) (firstHalfOfLen&0xFFFF);      	
		        
		    	    	return (short)calculateChecksum(extendedData);
		    	    	
		    }
		
		
		
		
	
	
	
	
	public static long calculateChecksum(byte[] buf) {
	    int length = buf.length;
	    int i = 0;

	    long sum = 0;
	    long data;

	    // Handle all pairs
	    while (length > 1) {
	      // Corrected to include @Andy's edits and various comments on Stack Overflow
	      data = (((buf[i] << 8) & 0xFF00) | ((buf[i + 1]) & 0xFF));
	      sum += data;
	      // 1's complement carry bit correction in 16-bits (detecting sign extension)
	      if ((sum & 0xFFFF0000) > 0) {
	        sum = sum & 0xFFFF;
	        sum += 1;
	      }

	      i += 2;
	      length -= 2;
	    }

	    // Handle remaining byte in odd length buffers
	    if (length > 0) {
	      // Corrected to include @Andy's edits and various comments on Stack Overflow
	      sum += (buf[i] << 8 & 0xFF00);
	      // 1's complement carry bit correction in 16-bits (detecting sign extension)
	      if ((sum & 0xFFFF0000) > 0) {
	        sum = sum & 0xFFFF;
	        sum += 1;
	      }
	    }

	    // Final 1's complement value correction to 16-bits
	    sum = ~sum;
	    sum = sum & 0xFFFF;
	    return sum;

	  }

	
	
	
}
