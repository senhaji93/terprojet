package IG;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class Simulation extends JFrame {
	private static final int D_W = 500;
    private static final int D_H = 9600;
    int x = 0;
    int y = 0;
    int ack[][]=new int[10][2000];
    int seq[][]=new int[10][2000];
    int status;
    int n=0;
    int l=0;
    int taille;
   DrawPanel drawPanel = new DrawPanel();
   ArrayList <Integer> list = new ArrayList<Integer>();

   public Simulation(int [][]ack,int[][]seq,int status,int t[]) {
	   System.out.println("-s-"+status+" taille"+t[0]);

	   
	   for(int c=0;c<t[status-1];c++) {
		   this.ack[status-1][c]=ack[status-1][c];
		   this.seq[status-1][c]=seq[status-1][c];
		   System.out.println(ack[status-1][c]+"  "+seq[status-1][c]);
	   }
	   System.out.println();
	   this.status=status-1;
	   this.taille=t[status-1];
	   
      setTitle("JScrollablePanel Test");
      setLayout(new BorderLayout());
      JPanel panel = createPanel();
      panel.add(drawPanel);
      add(BorderLayout.CENTER, new JScrollPane(panel));
      setSize(600, 400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setVisible(true);
      ActionListener listener2 = (ActionListener) new AbstractAction() {
          public void actionPerformed(ActionEvent e) {
           if(x<D_W&&seq[status-1][n]==ack[status-1][l]) {
          	   x += 10;
                  drawPanel.repaint();
            list.add(n);
         
             	 
                  
           }else {
          	 n++;
          	 if(seq[status-1][n]==ack[status-1][l]) {x = 0;}
          
          	 if(n==taille) {
          		 l++;
          		 n=0;
          		
          	 }
          	
           }
           if(l==taille) {
          	 n=taille;
          	 x=0; 	 
           }
             
                  
              
          }
      };	       
      	Timer timer2 = new Timer(20, listener2);
      	
      		timer2.start();
      	
   }
   public static JPanel createPanel() {
      JPanel panel = new JPanel();
  
      return panel;
   }
   private class DrawPanel extends JPanel {

       protected void paintComponent(Graphics g) {
           super.paintComponent(g);
           g.setColor(Color.yellow);
           g.fillRect(x, y, 50, 50);
          int p=0;
       
           for(int i=0;i<taille;i++) {
           	g.setColor(Color.LIGHT_GRAY);
	            g.fillRect(450, i*55, 50, 50);
	           
	            g.setColor(Color.red);
	            g.fillRect(0, i*55, 50, 50);
	            g.setColor(Color.black);
	            g.drawString( Integer.toString(ack[status][i]), 0, (i*35)+p);
	            g.drawString( Integer.toString(ack[status][0]), 0,35);
	           p=p+20;
	           // g.drawString( Integer.toString(ack[status][i]), 450, i*35);
	           
	       }
   
           		if(seq[status][n]==ack[status][l]) {
           			
           			g.setColor(Color.blue);
   		            g.fillRect(450, n*55, 50, 50);
   		            y=n*55;
           		}
          
           		for (int i = 0; i < list.size (); i++) {
           			list.get(i);
           			g.setColor(Color.green);
   		            g.fillRect(450, (list.get(i))*55, 50, 50);
   		       //  g.setColor(Color.black);
   		       //  g.drawString( Integer.toString(list.get(i)*500),450, list.get(i)*55);
           		}
           
       }

       public Dimension getPreferredSize() {
           return new Dimension(D_W, D_H);
      
       }
   }
 
}