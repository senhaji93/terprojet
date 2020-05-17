package IG;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;



import javax.swing.SwingConstants;

import selective.repeat.Client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PcConfigurationDialog extends JDialog {
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
  private Node node;
  public int status=0;
  public PcConfigurationDialog(JFrame parent, String title, boolean modal,Node node){
    super(parent, title, modal);
    this.node=node;
    this.setSize(1250, 650);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    this.initComponent();
  }

  public DialogInfoPc showZDialog(){
    this.sendData = false;
    this.setVisible(true);      
    return this.zInfo;      
  }

  private void initComponent(){
   

    //Le nomlocalehost
    JPanel panNom = new JPanel();
    panNom.setBackground(Color.white);
    panNom.setPreferredSize(new Dimension(220, 60));
    nom = new JTextField();
    nom.setText(node.getName());
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
    paneld.setBounds(300, 300, 200, 200);
    paneld.setBackground(Color.red);
    content.add(paneld);
   

    JPanel control = new JPanel();
    JButton okBouton = new JButton("configurer");
    JButton demarerBouton = new JButton("demarer");
    if(node.getPort().equals("0")) {
    	 control.add(okBouton);
    }else {
    	//okBouton.setVisible(false);
    	//  JLabel demarer = new JLabel("pc configurer demarer la transmission ");
	    //   	 control.add(demarerBouton);
	    //   	control.add(demarer);
    }
    demarerBouton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    		/*  try {
					node.c1.run();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} */
    	  }
    });
    	 okBouton.addActionListener(new ActionListener(){
    	      public void actionPerformed(ActionEvent arg0) {        
    	        zInfo = new DialogInfoPc(nom.getText(),prclient.getText(), prserver.getText(), nomfichierD.getText() ,nomfichierR.getText(),true);
    	       // Integer.parseInt(number);	
    	        /*    node.c1.clientPortNum=Integer.parseInt(prclient.getText());
    	        node.c1.serverPortNum=Integer.parseInt(prserver.getText());
    	        node.c1.requestFile= nomfichierD.getText();
    	        node.c1.outputFile=nomfichierR.getText();*/
    	        Client c1=new Client("localhost",  9875,  9877,  "receive.txt", "receiiiive.txt", 30);
    		  try {
				c1.run();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
    	        node.setPort(prclient.getText());
    	        if(node.getPort().equals("0")) {
    	        	System.out.println("equaaaaaal");
    	        	 prclient = new JTextField();
    	        }else {
    	        	
    	        	prclient.setEnabled(false);
    	        	okBouton.setVisible(false);
    	      	 JLabel demarer = new JLabel("pc configurer demarer la transmission ");
    	  	       control.add(demarerBouton);
    	  	      control.add(demarer);
    	        	
    	         
    	        }
    	        
    	      }
    	     
    	    });
    	

  
  
    
   
    JButton cancelBouton = new JButton("Annuler");
    cancelBouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {
    	
        setVisible(false);
      }      
    });

   
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
    
   
    if(node.getPort().equals("0")) {
    	System.out.println("equaaaaaal");
    	 prclient = new JTextField();
    }else {
    	prclient = new JTextField();
    	prclient.setText(node.getPort());
    	prclient.setEnabled(false);
    }
    panportc.add(prclient);
    prclient.setPreferredSize(new Dimension(100, 25));
    content.setLayout(gl_content);
    this.getContentPane().add(control, BorderLayout.SOUTH);
  }  

}