package IG;

import java.awt.EventQueue;
import javax.swing.JFrame;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import java.awt.Color;


import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import java.util.LinkedList;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.Cursor;


import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;

import javax.swing.event.ChangeEvent;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Panel;
import java.awt.Point;

import java.awt.BorderLayout;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.awt.Font;



public class ZoneTravail  extends JPanel{

	private JFrame frame;
	private JLabel lblserveur;
	
	private JLabel lblOrdinateur;
	

	private JRadioButton rdbtnserveur;
	private JRadioButton rdbtnPc;
	private ButtonGroup outils = new ButtonGroup();

	private JRadioButton rdbtnSelect;
	private JRadioButton rdbtnDelete;
	private ButtonGroup function = new ButtonGroup();
	private JRadioButton rdbtnDataTest;
	private JRadioButton rdbtnTest;
	private JScrollPane zonetravail;
	private Panel panel_Dessin;
	private JPanel panel_Outils;
	private JPanel panel_Function;
	private JPanel panel_ZoneDeTravaile;
	private int xStart;
	private int yStart;
	private int xFin;
	private int yFin;
	private Graphics2D g2;
	JLabel l = new JLabel("panel ddddddlabel"); 

	private ArrayList<Node> nodes=new ArrayList<Node>();
	private ArrayList<Node> selectedNode=new ArrayList<Node>();;
	///////////////////
	private Node courant=null;
	private Point2D lastPointPress;
	private int Toutselec;
	private BufferedImage image;
	private String imagefolder="C:\\Users\\PC\\Desktop\\yt\\tc\\Transport\\src\\images\\";

	////////////

	private Node testdejic1;
	private Node testdejic;
	LinkedList<Node> path;
	private JLabel label_1;
	private String url;
	
	Image img= new ImageIcon(imagefolder+"if_message_172503.png").getImage();
			//new ImageIcon("/home/user/Bureau/a.png").
	public int right,down;
	 Point debut,fin;
	 double x,y,a,c;
	 String tempf,tempd,date;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					java.util.Timer vgTimer = new java.util.Timer(); 
			
					ZoneTravail window = new ZoneTravail();
			
					window.frame.setVisible(true);
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	
	
		public ZoneTravail() {
		initialize();
	}

		 public String printTime() {
		       SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		       Date aujourdhui = new Date();
		       return dateFormat.format(aujourdhui);
		   }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 920, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		panel_Function = new JPanel();
	
		panel_Function.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panel_OutilsPrincipale = new JPanel();/////////////////////////////////
	
		panel_OutilsPrincipale.setBorder(new LineBorder(new Color(100, 200, 10)));
		
		
		
		panel_ZoneDeTravaile = new JPanel();
		panel_ZoneDeTravaile.setBackground(Color.WHITE);
		panel_ZoneDeTravaile.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ZoneDeTravaile.add(zonetravail=new JScrollPane(panel_Dessin));
		
		JPanel panel_ChoixAndResultat = new JPanel();
		panel_ChoixAndResultat.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_ZoneDeTravaile, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_Function, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_OutilsPrincipale, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
				.addComponent(panel_ChoixAndResultat, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
				
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_OutilsPrincipale, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_ZoneDeTravaile, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
						.addComponent(panel_Function, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_ChoixAndResultat, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(4))
		);
		
		panel_Dessin = new Panel();
		panel_Dessin.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				Point p = arg0.getPoint();
				if(courant==null) {
					System.out.println("mouse dragged sans avoire un courant " );
				
				if(rdbtnPc.isSelected()) {
					
					System.out.println("mouse dragged sans  courant =pc");
					panel_Dessin.update(g2);
					xFin=arg0.getX();
					yFin=arg0.getY();
					lastPointPress=arg0.getPoint();
					drawAll();try {
						image = ImageIO.read(getClass().getResource("/receptor.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		 g2.drawImage(image,arg0.getPoint().x,arg0.getPoint().y, null);	
				}
				if(rdbtnserveur.isSelected()) {
					//System.out.println("mouse dragged sans courant =Hub");
					panel_Dessin.update(g2);
					xFin=arg0.getX();
					yFin=arg0.getY();
					lastPointPress=arg0.getPoint();
					drawAll();

					drawAll();try {
						image = ImageIO.read(getClass().getResource("/lsr.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		 g2.drawImage(image,arg0.getPoint().x,arg0.getPoint().y, null);	
				}
				
				
				if(rdbtnDataTest.isSelected()||rdbtnDelete.isSelected()||rdbtnTest.isSelected()||rdbtnSelect.isSelected()){
					panel_Dessin.update(g2);
					drawAll();
				}
				
				}else {
					System.out.println("courant == "+courant.getName());
					if(rdbtnPc.isSelected()||rdbtnSelect.isSelected()
							) {
				//		System.out.println("mouse dragged avec courant ");
					double dx = p.getX() - lastPointPress.getX();
					double dy = p.getY() - lastPointPress.getY();			
				
				//	if (!selectedNode.contains(courant)) {
						
						panel_Dessin.update(g2);
						drawAll();
						courant.moveBy(dx, dy);
						
				
					lastPointPress = p;
				}else {
					
						if(rdbtnDelete.isSelected()) {
							
						//	System.out.println("mouse dragged avec courant"+courant.getName()+" et radio button Delete ");
							panel_Dessin.update(g2);
							xFin=arg0.getX();
							yFin=arg0.getY();
							lastPointPress=arg0.getPoint();
							Line2D lin=new Line2D.Float((float)courant.getRs().getCenterX(),(float)courant.getRs().getCenterY(),xFin,yFin);
							g2.draw(lin);
							drawAll();
						}else {
						System.out.println("mouse dragged avec courant mais radio button= delet=test=datatest ");
						panel_Dessin.update(g2);
						drawAll();
						}
						

					
				}
					}
				
			
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				if (find(arg0.getPoint())==null)
					panel_Dessin.setCursor(Cursor.getDefaultCursor());
					else
						panel_Dessin.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				
			}
		});
		panel_Dessin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("mouse pressed ");
				if(g2==null) {
					g2=(Graphics2D) panel_Dessin.getGraphics();
				}
				panel_Dessin.update(g2);
				xStart=e.getX();
				yStart=e.getY();
				xFin=e.getX();
				yFin=e.getY();
				Point p = e.getPoint();
				
				courant = find(p);
				if(courant==null) {
				System.out.println("mouse pressed initialisation du courant avec null");
		        if(nodes.isEmpty()) {
		        	
		        }
		        }
		        else {
					System.out.println("mouse pressed initialisation du courant avec find p  ("+courant.getName()+" )");
					
					if(rdbtnTest.isSelected()) {
					if(testdejic==null) {
						panel_Dessin.update(g2);
						testdejic=courant;
						drawAll();
					}else {
						panel_Dessin.update(g2);
						
						drawAll();
						
							
							int i=0;
							for (Node node : nodes) {
								if(node==testdejic) {
									break;
								}
								i++;
							}
							
							
							 i=0;
							for (Node node : nodes) {
								if(node==courant) {
									break;
								}
								i++;
							}
						
							
							
							try {
								String s="";
							for (Node vertex : path) {
								s+=vertex+" ->";
							}
						   tempd = printTime();
							for(int k=0;k<path.size()-1;k++) {
								debut=new Point((int)path.get(k).getRs().getCenterX()-20,(int)path.get(k).getRs().getCenterY()-20);
								fin=new Point((int)path.get(k+1).getRs().getCenterX()-20,(int)path.get(k+1).getRs().getCenterY()-20);
								x=debut.getX();
								y=debut.getY();
								if(fin.getX()+5< x || fin.getX()-5> x) {
									a=(fin.getY()-debut.getY())/(fin.getX()-debut.getX());
									c=debut.getY()-a*debut.getX();
									//y=xa+c;
									int z=(int)Math.sqrt(Math.pow((fin.getX()-x),2)+ Math.pow((fin.getY()-y),2));
									int d=z/5;
									int h=d;
										while(h<z-1) {
											h+=d;
											g2.drawImage(img,(int)x-20,(int)y-20,40,40,null);
									if(x> fin.getX() ) {
													x-=d;
													y=a*x+c;
												
												}else {
													if(x<fin.getX()) {
														x+=d;
														y=a*x+c;
													}
												
											}
												
											Thread.sleep(800);
											panel_Dessin.update(g2);
											drawAll();
										
										}
										panel_Dessin.update(g2);
										drawAll();	
								}else {
									int h=0,f=0,t=1;
									int w=(int) Math.abs(fin.getY()-y);
									if(w<4) {
										h=1;
									}
									else
										h=w/4;
									
									while((f<4 && t<2 ) ) {
										g2.drawImage(img,(int)x-20,(int)y-20,40,40,null);
											if(h==1) t++;
										if(y> fin.getY()) {
											y-=h;
											
										}else {
												if(y<fin.getY()) {
														y+=h;
												}
										}
										Thread.sleep(800);
										panel_Dessin.update(g2);
										drawAll();
										f++;
									}
									g2.drawImage(img,(int)x-20,(int)y-20,40,40,null);
									Thread.sleep(800);
									panel_Dessin.update(g2);
									drawAll();
								}
							
										
										
						}
							
						    tempf = printTime();
							
							
							//label_1.setText("debut :"+debut.getX()+"et"+debut.getY()+"\nfin :"+fin.getX()+" et "+fin.getY());
							}
							catch(Exception e1) {
								label_1.setText("maakaynach");
							}
							
							testdejic=null;
					}
						
						
						
						
						
					}
					if(rdbtnDataTest.isSelected()) {
						if(testdejic1==null) {
							panel_Dessin.update(g2);
							testdejic1=courant;
							JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

							int returnValue = jfc.showOpenDialog(null);
							// int returnValue = jfc.showSaveDialog(null);

							if (returnValue == JFileChooser.APPROVE_OPTION) {
								File selectedFile = jfc.getSelectedFile();
								System.out.println("bqth"+selectedFile.getAbsolutePath());
								url=selectedFile.getAbsolutePath();
							}
							drawAll();
						}else {
							
								
								testdejic1=null;
						}
							
							
							
							
							
						}
					
					
					
					
				}
					
					
					
					
					
					
				
				
				lastPointPress = p;
			//	System.out.println("mouse pressed initialisation du last poinPoin pressed  avec  p ( "+lastPointPress.getX()+"  "+
			//			lastPointPress.getY()+"  )");
			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.print("mouse released ");
				lastPointPress=e.getPoint();
				if(courant==null) {
					System.out.print(" avec courant =null ");
				
				if(rdbtnPc.isSelected()) {
					System.out.println(" et radiobutton = pc dessin pc");
					panel_Dessin.update(g2);
					xFin=e.getX();
					yFin=e.getY();		
					nodes.add(new pc("PC"+(nombreInstance(new pc())+1),new Rectangle2D.Float((float)xFin-20,(float)yFin-20,40,40)));
					drawAll();
	
				}
				if(rdbtnserveur.isSelected()) {
					//System.out.println(" et radiobutton = pc dessin hub");
					panel_Dessin.update(g2);
					xFin=e.getX();
					yFin=e.getY();					
					nodes.add(new Serveur("serveur"+(nombreInstance(new Serveur())+1),new Rectangle2D.Float((float)xFin-20,(float)yFin-20,50,50)));
					drawAll();
				}
				
				
				}else {
					System.out.println("courant = "+courant.getName());
					
					if (rdbtnDelete.isSelected()) {	
						
						System.out.println("les arcs disponible sont  ");

						
						
					}
					
				}
				
			
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.print("mouse clicked ");
				
				if(courant==null) {
					
					System.out.println("et courant =null de mause clicked");
					drawAll();
				}else {
					System.out.println("et courant =="+courant.getName());
					if(rdbtnDelete.isSelected()) {
						
					
					
					int i=0;
					for (Node node : nodes) {
						
						if(node==courant)
						{System.out.println("  "+nodes.indexOf(courant)+"        "+node.getName()+"><<<<<<<<" );
						break;
						}
						i++;
					}	
				
					nodes.remove(i);
					panel_Dessin.update(g2);
					drawAll();
					}
					if(rdbtnPc.isSelected()||rdbtnSelect.isSelected()
							||rdbtnserveur.isSelected()	) {
					if(courant instanceof pc) {
						for(Node node:nodes) {
							if(node==courant) {
								drawAll();
								System.out.print(" nom du  1lpc "+node.getName());	
								PcConfigurationDialog zd = new PcConfigurationDialog(null, "configuration de "+node.getName(), true,node);				
								DialogInfoPc zInfo = zd.showZDialog(); 
						        if(zInfo.isConfigured()) {
						        	System.out.println("pc bien configurer");
						        	node.setNom(zInfo.getNom());
						        }else {
						        	drawAll();
						        //	System.out.println("configuration annuler");

						        }
						        //JOptionPane jop = new JOptionPane();
						       // jop.showMessageDialog(null,zInfo.toString(), "Informations de "+node.getName(), JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					if(courant instanceof Serveur) {
						for(Node node:nodes) {
							if(node==courant) {
								drawAll();
								//System.out.print("nom du  Hub "+node.getName());
								HubConfigurationDialog zd = new HubConfigurationDialog(null, "configuration de "+node.getName(), true,node);
						        DialogInfoHub zInfo = zd.showZDialog(); 
						        if(zInfo.isConfigured()) {
						        //	System.out.println("hub bien configurer");
						        	node.setNom(zInfo.getNom());
						        }else {
						        	drawAll();
						        	System.out.println("configuration annuler");

						        }
						       // JOptionPane jop = new JOptionPane();
						       // jop.showMessageDialog(null,zInfo.toString(), "Informations "+node.getName(), JOptionPane.INFORMATION_MESSAGE);
							}
						}}
					
					
					
					}

					
				}
			}
		});

		panel_Dessin.setBackground(Color.WHITE);
		panel_ZoneDeTravaile.setLayout(new BorderLayout(0, 0));
		panel_ZoneDeTravaile.add(zonetravail);
		panel_ZoneDeTravaile.add(panel_Dessin);
		
		
		
		panel_Outils = new JPanel();
	
		panel_Outils.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		label_1 = new JLabel("simulation tcp ip client serveur");
		GroupLayout gl_panel_ChoixAndResultat = new GroupLayout(panel_ChoixAndResultat);
		gl_panel_ChoixAndResultat.setHorizontalGroup(
			gl_panel_ChoixAndResultat.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_ChoixAndResultat.createSequentialGroup()
					.addComponent(panel_Outils, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(label_1)
					.addContainerGap(501, Short.MAX_VALUE))
		);
		gl_panel_ChoixAndResultat.setVerticalGroup(
			gl_panel_ChoixAndResultat.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_Outils, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
				.addGroup(gl_panel_ChoixAndResultat.createSequentialGroup()
					.addGap(23)
					.addComponent(label_1)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		panel_Outils.setLayout(null);
		
		lblOrdinateur = new JLabel("ORDINATEUR");
		lblOrdinateur.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblOrdinateur.setBounds(58, 51, 61, 14);
		panel_Outils.add(lblOrdinateur);
		
		
		lblserveur= new JLabel("Serveur");
		lblserveur.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		lblserveur.setBounds(11, 51, 46, 14);
		panel_Outils.add(lblserveur);
		
		
		
	
		
		rdbtnserveur= new JRadioButton(new ImageIcon(imagefolder+"cable.png"));
		lblserveur.setLabelFor(rdbtnserveur);
		rdbtnserveur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function.clearSelection();
			}
		});
		rdbtnserveur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnserveur.setBackground(SystemColor.controlShadow);
			}
		});
		rdbtnserveur.setToolTipText("serveur");
		rdbtnserveur.setBackground(SystemColor.scrollbar);
		rdbtnserveur.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnserveur.isSelected()) {
					rdbtnserveur.setBackground(Color.GREEN);
				}else {
					rdbtnserveur.setBackground(SystemColor.scrollbar);
				}
			}
		});
		rdbtnserveur.setBounds(1, 1, 50, 50);
		panel_Outils.add(rdbtnserveur);
		
		
		rdbtnPc = new JRadioButton(new ImageIcon(imagefolder+"receptor.png"));
		lblOrdinateur.setLabelFor(rdbtnPc);
		rdbtnPc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function.clearSelection();
			}
		});
		rdbtnPc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnPc.setBackground(SystemColor.controlShadow);
			}
		});
		rdbtnPc.setToolTipText("ordinateur");
		rdbtnPc.setBackground(SystemColor.scrollbar);
		rdbtnPc.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnPc.isSelected()) {
					rdbtnPc.setBackground(Color.GREEN);
				}else {
					rdbtnPc.setBackground(SystemColor.scrollbar);
				}
				
			}
		});
		rdbtnPc.setBounds(61, 1, 50, 50);
		panel_Outils.add(rdbtnPc);
		
		
		outils.add(rdbtnserveur);
		outils.add(rdbtnPc);
		
	
		panel_ChoixAndResultat.setLayout(gl_panel_ChoixAndResultat);
		panel_Function.setLayout(null);
		
		rdbtnSelect = new JRadioButton(new ImageIcon(imagefolder+"if_hand_cursor_2639827 (3).png"));
		rdbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outils.clearSelection();
			}
		});
		rdbtnSelect.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnSelect.isSelected()) {
					rdbtnSelect.setBackground(Color.GREEN);
				}else {
					rdbtnSelect.setBackground(SystemColor.inactiveCaption);
				}
			}
		});
		rdbtnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnSelect.setBackground(SystemColor.activeCaption);
			}
		});
		rdbtnSelect.setBounds(6, 6, 40, 40);
		rdbtnSelect.setToolTipText("s�lectionn�");
		
		rdbtnSelect.setBackground(SystemColor.inactiveCaption);
		panel_Function.add(rdbtnSelect);
		
		rdbtnDelete = new JRadioButton(new ImageIcon(imagefolder+"if_meanicons_24_197210.png"));
		rdbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outils.clearSelection();
			}
		});
		rdbtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnDelete.setBackground(SystemColor.activeCaption);
			}
		});
		rdbtnDelete.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnDelete.isSelected()) {
					rdbtnDelete.setBackground(Color.GREEN);
				}else {
					rdbtnDelete.setBackground(SystemColor.inactiveCaption);
				}
			}
		});
		rdbtnDelete.setBounds(6, 56, 40, 40);
		rdbtnDelete.setToolTipText("effacer");
		rdbtnDelete.setBackground(SystemColor.inactiveCaption);
		panel_Function.add(rdbtnDelete);
		
		rdbtnTest = new JRadioButton(new ImageIcon(imagefolder+"if_message_172503.png"));
		rdbtnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outils.clearSelection();
			}
		});
		rdbtnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnTest.setBackground(SystemColor.activeCaption);
			}
		});
		rdbtnTest.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnTest.isSelected()) {
					rdbtnTest.setBackground(Color.GREEN);
				}else {
					rdbtnTest.setBackground(SystemColor.inactiveCaption);
				}
			}
		});
		rdbtnTest.setBackground(SystemColor.inactiveCaption);
		rdbtnTest.setToolTipText("verifier la connection");
		
		
		
		
		rdbtnTest = new JRadioButton(new ImageIcon(imagefolder+"if_ic_attach_file_48px_352032.png"));
		rdbtnTest.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		outils.clearSelection();
		 	}
		 });
		rdbtnTest.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseEntered(MouseEvent e) {
		 		rdbtnTest.setBackground(SystemColor.activeCaption);
		 	}
		 	
		 });
		rdbtnTest.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent e) {
		 		if(rdbtnTest.isSelected()) {
		 			rdbtnTest.setBackground(Color.GREEN);
				}else {
					rdbtnTest.setBackground(SystemColor.inactiveCaption);
				}
		 	}
		 });
		
		
		
		
		
		
		
		
		
		
		
		
		
		rdbtnTest.setBounds(6, 106, 40, 40);
		panel_Function.add(rdbtnTest);
		
		 rdbtnDataTest = new JRadioButton(new ImageIcon(imagefolder+"if_message_172503 .png"));
		 rdbtnDataTest.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		outils.clearSelection();
		 	}
		 });
		 rdbtnDataTest.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseEntered(MouseEvent e) {
		 		rdbtnDataTest.setBackground(SystemColor.activeCaption);
		 	}
		 	
		 });
		 rdbtnDataTest.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent e) {
		 		if(rdbtnDataTest.isSelected()) {
					rdbtnDataTest.setBackground(Color.GREEN);
				}else {
					rdbtnDataTest.setBackground(SystemColor.inactiveCaption);
				}
		 	}
		 });
		rdbtnDataTest.setBackground(SystemColor.inactiveCaption);
		rdbtnDataTest.setToolTipText("envoyer un fichier");
		rdbtnDataTest.setBounds(6, 156, 40, 40);
		panel_Function.add(rdbtnDataTest);
		frame.getContentPane().setLayout(groupLayout);
		function.add(rdbtnDataTest);
		function.add(rdbtnTest);
		function.add(rdbtnDelete);
		function.add(rdbtnSelect);
	}
	 private void drawAll() {
		// System.out.println("taille d'arc est  "+arcs.size());
		
	
            // image = ImageIO.read(getClass().getResource("/icons8-hub-32.png"));
             for (Node node : nodes) {
            	 if (node instanceof pc){
            		 try
            		    {
            		      // the line that reads the image file
            			 
            		      BufferedImage image = ImageIO.read(new File("C:\\Users\\PC\\Desktop\\yt\\tc\\Transport\\src\\images\\receptor.png"));
            		      g2.drawImage(image,(int) node.getRs().getCenterX()-40, (int) node.getRs().getCenterY()-20, null);	
                 		 g2.drawString(node.getName(), (int) node.getRs().getCenterX()+5,(int) node.getRs().getCenterY()-20);
                 	
            		      // work with the image here ...
            		    } 
            		    catch (IOException e)
            		    {
            		      // log the exception
            		      // re-throw if desired
            		    }
            		 
            		
            		 }
            	 if (node instanceof Serveur){
       		      BufferedImage image;
				try {
					image = ImageIO.read(new File("C:\\Users\\PC\\Desktop\\yt\\tc\\Transport\\src\\images\\cable.png"));
					 g2.drawImage(image,(int) node.getRs().getCenterX()-20, (int) node.getRs().getCenterY()-20, null);
            		 g2.drawString(node.getName(), (int) node.getRs().getCenterX()+5,(int) node.getRs().getCenterY()-20);
            	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            		 }
            	
    			
    			}
	 }
	 public Node find(Point2D p) {

			for (Node node : nodes) {
				if (node.getRs().contains(p)) {
					return node;
				}
				
			}
			return null;
		}
	private  int nombreInstance(Node n){
		int i=0;
		if(n instanceof pc) {
			for (Node node : nodes) {
				if(node instanceof pc) {
				i++;	
				}
			}
		}
		if(n instanceof Serveur) {
			for (Node node : nodes) {
				if(node instanceof Serveur) {
				i++;	
				}
			}
		}
		
		return i;
	}

}