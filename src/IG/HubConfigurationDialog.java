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

import selective.repeat.Server;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HubConfigurationDialog extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private DialogInfoHub zInfo = new DialogInfoHub();
  private boolean sendData;
  private JLabel nomLabel, pclient, lblfd, lblfr;
  private JTextField nom, jwindo;
  private JTextField prtserver;
  private JTextField Jseed;
  private JTextField Jpanprobability;
  private Node node;
  public HubConfigurationDialog(JFrame parent, String title, boolean modal,Node node){
    super(parent, title, modal);
    this.node=node;
    this.setSize(1250, 650);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    this.initComponent();
  }

  public DialogInfoHub showZDialog(){
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
    panNom.setBorder(BorderFactory.createTitledBorder("nom serveur"));
    nomLabel = new JLabel("Saisir Nom :");
    panNom.add(nomLabel);
    panNom.add(nom);

    //Le panportc
    JPanel panports = new JPanel();
    panports.setBackground(Color.white);
    panports.setPreferredSize(new Dimension(220, 60));
    panports.setBorder(BorderFactory.createTitledBorder("port serveur"));
    

    
    pclient = new JLabel("   port : ");
    pclient.setHorizontalAlignment(SwingConstants.RIGHT);
    panports.add(pclient);
    

    //panserver
    JPanel panserverwindowSize = new JPanel();
    panserverwindowSize.setBackground(Color.white);
    panserverwindowSize.setBorder(BorderFactory.createTitledBorder("windowSize "));
    panserverwindowSize.setPreferredSize(new Dimension(440, 60));
    ButtonGroup bg = new ButtonGroup();

    //fichier a recevoir
    JPanel panseed = new JPanel();
    panseed.setBackground(Color.white);
    panseed.setPreferredSize(new Dimension(220, 60));
    panseed.setBorder(BorderFactory.createTitledBorder("seed"));
    lblfr = new JLabel("seed:");
    panseed.add(lblfr);
    Jseed = new JTextField("");
    //jwindo.setEnabled(false);////////iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
    Jseed.setPreferredSize(new Dimension(120, 25));
    panseed.add(Jseed);

    //fichier demander
    JPanel panprobability = new JPanel();
    panprobability .setBackground(Color.white);
    panprobability .setPreferredSize(new Dimension(220, 60));
    panprobability .setBorder(BorderFactory.createTitledBorder("probability"));
    lblfd = new JLabel("probability :");
    panprobability .add(lblfd);

    JPanel content = new JPanel();
    content.setBackground(Color.WHITE);
    
    JPanel paneld= new JPanel();
    paneld.setBounds(300, 300, 200, 200);
    paneld.setBackground(Color.red);
    content.add(paneld);
   

    JPanel control = new JPanel();
    JButton okBouton = new JButton("OK");
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
   		/* try {
   			setVisible(false);
			node.s1.run();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
   	  }
   });
    okBouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {        
        zInfo = new DialogInfoHub(nom.getText(),prtserver.getText(), jwindo.getText(), Jpanprobability.getText(),Jseed.getText() ,true);
      //  node.setPort(prtserver.getText());
       //  node.c1.clientPortNum=Integer.parseInt(prclient.getText());
       // node.s1.probability=Integer.parseInt( Jpanprobability.getText());
        //node.s1.seed=Integer.parseInt(Jseed.getText());
       // node.s1.serverPortNum=Integer.parseInt(prtserver.getText());
        Server s1=new Server(9875
        		,10
        		,100
        		,0.01);
        try {
			s1.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       // node.nom=nom.getText();
    /*    
        if(node.getPort().equals("0")) {
        	System.out.println("equaaaaaal");
        	prtserver = new JTextField();
        }else {
        	
        	prtserver.setEnabled(false);
        	okBouton.setVisible(false);
      	  JLabel demarer = new JLabel("serveur configurer demarer  ");
  	       	 control.add(demarerBouton);
  	       	control.add(demarer);
  	   
         
        }*/
      }
     
    });

    JButton cancelBouton = new JButton("Annuler");
    cancelBouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {
    	
        setVisible(false);
      }      
    });

    control.add(okBouton);
    control.add(cancelBouton);

    
    this.getContentPane().add(content, BorderLayout.CENTER);
    GroupLayout gl_content = new GroupLayout(content);
    gl_content.setHorizontalGroup(
    	gl_content.createParallelGroup(Alignment.LEADING)
    		.addGroup(gl_content.createSequentialGroup()
    			.addGap(50)
    			.addGroup(gl_content.createParallelGroup(Alignment.TRAILING, false)
    				.addComponent(panseed, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    				.addComponent(panNom, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
    				.addComponent(panports, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    				.addComponent(panserverwindowSize, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    				.addComponent(panprobability , Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    					.addComponent(panports, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(panserverwindowSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(panprobability , GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(panseed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    			.addContainerGap(168, Short.MAX_VALUE))
    );
    
   Jpanprobability = new JTextField();
   Jpanprobability.setPreferredSize(new Dimension(100, 25));
    panprobability .add(Jpanprobability);
    Jpanprobability.setColumns(10);
    
    JLabel lblserveurwin = new JLabel("      windowSize  : ");
    lblserveurwin.setHorizontalAlignment(SwingConstants.RIGHT);
    panserverwindowSize.add(lblserveurwin);
    
    jwindo = new JTextField();
    jwindo.setPreferredSize(new Dimension(100, 25));
    panserverwindowSize.add(jwindo);
    jwindo.setColumns(10);
    
   
    
    if(node.getPort().equals("0")) {
    	System.out.println("equaaaaaal");
    	 prtserver = new JTextField();
    }else {
    	 prtserver = new JTextField();
    	 prtserver.setText(node.getPort());
    	 prtserver.setEnabled(false);
    }
    panports.add(prtserver);
    prtserver.setPreferredSize(new Dimension(100, 25));
    content.setLayout(gl_content);
    this.getContentPane().add(control, BorderLayout.SOUTH);
  }  

}