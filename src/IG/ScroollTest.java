package IG;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class ScroollTest extends JFrame {
	private static final int D_W = 500;
    private static final int D_H = 9600;
    int x = 0;
    int y = 0;
   int ack[]= {0,1,2,3,10,5,6,7,8,9};
   int seq[]= {0,1,2,3,4,5,6,7,10,9};
   int n=0;
   int l=0;
   DrawPanel drawPanel = new DrawPanel();
   ArrayList <Integer> list = new ArrayList<Integer>();

   public ScroollTest () {
	   
	   
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
           if(x<D_W&&seq[n]==ack[l]) {
          	   x += 10;
                  drawPanel.repaint();
            list.add(n);
         
             	 
                  
           }else {
          	 n++;
          	 if(seq[n]==ack[l]) {x = 0;}
          
          	 if(n==9) {
          		 l++;
          		 n=0;
          		
          	 }
          	
           }
           if(l==9) {
          	 n=9;
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
          
           for(int i=0;i<10;i++) {
           	g.setColor(Color.LIGHT_GRAY);
	            g.fillRect(450, i*55, 50, 50);
	            g.setColor(Color.red);
	            g.fillRect(0, i*55, 50, 50);
	       }
   
           		if(seq[n]==ack[l]) {
           			
           			g.setColor(Color.blue);
   		            g.fillRect(450, n*55, 50, 50);
   		            y=n*55;
           		}
          
           		for (int i = 0; i < list.size (); i++) {
           			list.get(i);
           			g.setColor(Color.green);
   		            g.fillRect(450, list.get(i)*55, 50, 50);
           		}
           
       }

       public Dimension getPreferredSize() {
           return new Dimension(D_W, D_H);
      
       }
   }
   public static void main(String [] args) {
      new ScroollTest ();
   }
}