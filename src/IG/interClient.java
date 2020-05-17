package IG;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import selective.repeat.Client;

public class interClient extends JFrame {
	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	private DialogInfoPc zInfo = new DialogInfoPc();
	  private boolean sendData;
	
	  private JLabel nomLabel, pclient, lblfd, lblfr;
	  private JTextField nom, nomfichierR;
	  private JTextField prclient;
	  private JTextField prserver;
	  private JTextField nomfichierD;
	    JButton []filename =new JButton[10] ;
	    JButton []infofile =new JButton[10] ;
	    JButton []sekask =new JButton[10] ;
	    JButton []simulation=new JButton[10];
	    String []filenames =new String[10] ;
	    String []infofiles =new String[10] ;
	    String []sekasks =new String[10] ;
	    String []simulations=new String[10];
	    int[][] ack = new int[10][2000];
	    int[][] seq = new int[10][2000];
	    int[]taskseq= new int[10];
	    
	  public int status=0;
	  public int status2=0;
	  public interClient(JFrame parent, String title, boolean modal){
		    super();
	    
	    this.setSize(1250, 650);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.initComponent();
	  }

	 

	  private void initComponent(){
	   

	    //Le nomlocalehost
	    JPanel panNom = new JPanel();
	    panNom.setBackground(Color.white);
	    panNom.setPreferredSize(new Dimension(220, 60));
	    nom = new JTextField();
	
	    nom.setPreferredSize(new Dimension(100, 25));
	    panNom.setBorder(BorderFactory.createTitledBorder("localehost"));
	    nomLabel = new JLabel("Saisir localhost :");
	    panNom.add(nomLabel);
	    panNom.add(nom);
	   
	    //Le panportc
	    JPanel panportc = new JPanel();
	    panportc.setBackground(Color.white);
	    panportc.setPreferredSize(new Dimension(220, 60));
	    panportc.setBorder(BorderFactory.createTitledBorder("port client"));
	    

	    
	    pclient = new JLabel("   port : ");
	    pclient.setHorizontalAlignment(SwingConstants.RIGHT);
	    panportc.add(pclient);
	    

	    //panserver
	    JPanel panserver = new JPanel();
	    panserver.setBackground(Color.white);
	    panserver.setBorder(BorderFactory.createTitledBorder("porte serveur "));
	    panserver.setPreferredSize(new Dimension(440, 60));
	    ButtonGroup bg = new ButtonGroup();

	    //fichier a recevoir
	    JPanel panfichierR = new JPanel();
	    panfichierR.setBackground(Color.white);
	    panfichierR.setPreferredSize(new Dimension(220, 60));
	    panfichierR.setBorder(BorderFactory.createTitledBorder("nom fichier a recevoir"));
	    lblfr = new JLabel("nom fichier a recevoir:");
	    panfichierR.add(lblfr);
	    nomfichierR = new JTextField("");
	    //nomfichierR.setEnabled(false);////////iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
	    nomfichierR.setPreferredSize(new Dimension(120, 25));
	    panfichierR.add(nomfichierR);

	    //fichier demander
	    JPanel panfichierD = new JPanel();
	    panfichierD.setBackground(Color.white);
	    panfichierD.setPreferredSize(new Dimension(220, 60));
	    panfichierD.setBorder(BorderFactory.createTitledBorder("fichier demander"));
	    lblfd = new JLabel("fichier demander :");
	    panfichierD.add(lblfd);

	    JPanel content = new JPanel();
	    content.setBackground(Color.WHITE);
	    
	    JPanel paneld= new JPanel();
	    paneld.setBounds(550, 10,650, 500);
	    paneld.setBackground(Color.red);

	      
	 
		paneld.setLayout(new FlowLayout());
		
	   
	  
	   
	    
	    
	    content.add(paneld);
	    JPanel control = new JPanel();
	    JButton okBouton = new JButton("configurer");
	    JButton demarerBouton = new JButton("afficher info");
	   
	    demarerBouton.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent arg0) {
	    		  if (status2<status) {
	    			  JLabel labelinfo =new JLabel("info                     k1:"+status);
	    	  		     filename[status2] = new JButton("fichier recu"+status);
	    	  		   filename[status2].setName("fichier recu"+status);
		    		   infofile[status2] = new JButton("packet info"+status);
		    		   infofile[status2].setName("packet info"+status);
		    		    sekask[status2] = new JButton("echange ACK et SEK"+status);
		    		    sekask[status2].setName("echange ACK et SEK"+status);
		    		    simulation[status2] = new JButton("simulation"+status);
		    		    simulation[status2].setName("simulation"+status);
		    		    filename[status2].addActionListener(new ActionListener() {
		    		    	  public void actionPerformed(ActionEvent arg0) {
		    		    		  System.out.println(((JButton) arg0.getSource()).getName()+" Click");
		    		    		  String s="C:\\Users\\PC\\Desktop\\dossier"+prclient.getText();
		  		    	        Desktop desktop = Desktop.getDesktop();
		  		    	      
		  		    	        //let's try to open PDF file
		  		    	      File file = new File(s+"\\"+nomfichierR.getText());
		  		    	    System.out.println(s+"\\"+nomfichierR.getText());
		  		    	        if(file.exists())
		  							try {
		  								desktop.open(file);
		  							} catch (IOException e) {
		  								// TODO Auto-generated catch block
		  								e.printStackTrace();
		  							}
		    		    	  } });
		    		   
		    		    
		    		    infofile[status2].addActionListener(new ActionListener() {
		    		    	  public void actionPerformed(ActionEvent arg0) {} });
		    		    
		    		   
		    		    sekask[status2].addActionListener(new ActionListener() {
		    		    	  public void actionPerformed(ActionEvent arg0) {
		    		    		  String s="C:\\Users\\PC\\Desktop\\dossier"+prclient.getText();
		    		    	
			  		    	        Desktop desktop = Desktop.getDesktop();
			  		    		    System.out.println(s+"\\"+nomfichierR.getText()+".txt");
			  		    	        //let's try to open PDF file
			  		    	      File file = new File(s+"\\"+nomfichierR.getText()+".txt");
			  		    	    System.out.println(s);
			  		    	        if(file.exists())
			  							try {
			  								desktop.open(file);
			  							} catch (IOException e) {
			  								// TODO Auto-generated catch block
			  								e.printStackTrace();
			  							}
		    		    	  } });
		    		    
		    		    
		    		    simulation[status2].addActionListener(new ActionListener() {
		    		    	  public void actionPerformed(ActionEvent arg0) {
		    		    		  System.out.println(((JButton) arg0.getSource()).getName()+" Click");
		    		    		  Simulation s=new Simulation(ack,seq,status,taskseq);
		    		    		  s.setVisible(true);
		    		    	  } });
		    		    
		    		    paneld.add(labelinfo);
		    		    paneld.add(filename[status2]);
		    		    
		    		    paneld.add(infofile[status2]);
		    		    paneld.add(sekask[status2]);
		    		    paneld.add(simulation[status2]);
		    		    content.validate();//valider le suppression
		    	
		    	        //faire des changement
		    	 
		    		    content.revalidate();
		    		    paneld.repaint();
		    		    content.repaint();
		    		    status2++;
	    		  }
	    		 
	    	  }
	    });
	    	 okBouton.addActionListener(new ActionListener(){
	    	      public void actionPerformed(ActionEvent arg0) {        
	    	       // Integer.parseInt(number);	
	    	        /*    node.c1.clientPortNum=Integer.parseInt(prclient.getText());
	    	        node.c1.serverPortNum=Integer.parseInt(prserver.getText());
	    	        node.c1.requestFile= nomfichierD.getText();
	    	        node.c1.outputFile=nomfichierR.getText();*/
	    	 
	    	    	  
	    	    	  String s="C:\\Users\\PC\\Desktop\\dossier"+prclient.getText();
	    		  
	    	    	  File files = new File(s);
	    	          if (!files.exists()) {
	    	              if (files.mkdirs()) {
	    	                  System.out.println("Multiple directories are created!");
	    	              } else {
	    	                  System.out.println("Failed to create multiple directories!");
	    	              }
	    	          }
	    	      
	   	    	   Client c1=new Client("localhost", Integer.parseInt(prserver.getText()),  Integer.parseInt(prclient.getText()), nomfichierD.getText(),s+"\\"+nomfichierR.getText(), 30);
	    		  try {
					c1.run();
				    JLabel info = new JLabel("Client numero connecte au serveur numero  : "+prclient.getText()+prserver.getText());
				    control.add(info);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    			 FileWriter myWriteri = null;
	    			    
	    				try {
	    					
	    					myWriteri = new FileWriter( s+"\\"+nomfichierR.getText()+".txt");
	    					
	    					                         
	    				} catch (IOException e1) {
	    					// TODO Auto-generated catch block
	    					e1.printStackTrace();
	    				}
	    		    try {
	    		    	RandomAccessFile file1 = new RandomAccessFile("C:\\Users\\PC\\Desktop\\ser"+prserver.getText() +".txt", "r");
	    	  			  			String str ,str2,str3=file1.readLine();
	    	  			  		RandomAccessFile file = new RandomAccessFile("C:\\Users\\PC\\Desktop\\ack"+prserver.getText()+"."+prclient.getText()+str3+".txt", "r");
	    	    	  			RandomAccessFile file2 = new RandomAccessFile("C:\\Users\\PC\\Desktop\\sen"+prserver.getText()+"."+prclient.getText()+"."+str3+".txt", "r");
	    	    	int p=0;
	    	  			while (((str = file.readLine()) != null)&&((str2 = file2.readLine()) != null)) {
	    	  				
	    	  				myWriteri.write(str);
	    	  				myWriteri.write("\n"+str2+"\n");
	    	  				if(str.length()!=0 || str2.length()!=0) {
	    	  				
	 	    	  				
	 	    	  			   ack[status][p]=Integer.parseInt(str.replaceAll("[\\D]", ""));
	 	    	  			   seq[status][p]=Integer.parseInt(str2.replaceAll("[\\D]", ""));
	 	    	  			 System.out.println("jjjjjjj"+ack[status][p]+"  "+seq[status][p]);
	 	    	  			   taskseq[status]=p;
	 	    	  			   p=p+1;
	    	  				}
	    	  				
	    	  				;
	    	  				
	    	  			}
	    	  			myWriteri.close();
	    	  			System.out.println(str3);
	    	  		} catch (IOException e) {
	    	  			e.printStackTrace();
	    	  		}
	    	     
	    	          filenames[status]=s+"\\"+nomfichierR.getText();
	    	          status++;
	    	      }
	    	     
	    	    });
	    	

	  
	  
	    
	   
	    JButton cancelBouton = new JButton("Annuler");
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	    	
	        setVisible(false);
	      }      
	    });

	    control.add(okBouton);
	    control.add(demarerBouton);
	    
	    control.add(cancelBouton);

	    
	    this.getContentPane().add(content, BorderLayout.CENTER);
	    GroupLayout gl_content = new GroupLayout(content);
	    gl_content.setHorizontalGroup(
	    	gl_content.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_content.createSequentialGroup()
	    			.addGap(50)
	    			.addGroup(gl_content.createParallelGroup(Alignment.TRAILING, false)
	    				.addComponent(panfichierR, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    				.addComponent(panNom, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
	    				.addComponent(panportc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    				.addComponent(panserver, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    				.addComponent(panfichierD, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    			.addGap(222)
	    			
	    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    );
	    gl_content.setVerticalGroup(
	    	gl_content.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_content.createSequentialGroup()
	    			.addGap(5)
	    			.addComponent(panNom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_content.createSequentialGroup()
	    					.addGap(27))
	    				.addGroup(gl_content.createSequentialGroup()
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(panportc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(panserver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(panfichierD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(panfichierR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap(168, Short.MAX_VALUE))
	    );
	    
	    nomfichierD = new JTextField();
	    nomfichierD.setPreferredSize(new Dimension(100, 25));
	    panfichierD.add(nomfichierD);
	    nomfichierD.setColumns(10);
	    
	    JLabel lblserveur = new JLabel("      porte serveur  : ");
	    lblserveur.setHorizontalAlignment(SwingConstants.RIGHT);
	    panserver.add(lblserveur);
	    
	    prserver = new JTextField();
	    prserver.setPreferredSize(new Dimension(100, 25));
	    panserver.add(prserver);
	    prserver.setColumns(10);
	    
	    prclient = new JTextField();
	    panportc.add(prclient);
	    prclient.setPreferredSize(new Dimension(100, 25));
	   
	   
	    content.setLayout(gl_content);
	    this.getContentPane().add(control, BorderLayout.SOUTH);
	  } 
	  public static void main(String[] args) {
		  interClient client=new interClient(null, "configuration de client ", true);
      client.setVisible(true);
		}  

}


