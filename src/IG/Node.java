
package IG;
import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Port;



import selective.repeat.Client;

import selective.repeat.Server;

public class Node{

    private String name;//identificacion du node
    private RectangularShape rs;
    private String port;
   // public Client c1=new Client();
   // public Server s1=new Server();
	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}


	private boolean selected;
	private String id;
	
	protected String nom;
	public static String adresse_mac;
	public static String adresse_mac_destination;
	protected int nombre_port;
	protected List<Port> ports;
	protected Float horizontal_position;
	protected Float vertival_position;

	protected void envoyer(String url) throws IOException {
		
	}
	

	public Node() {
	
	}
	


	public String getId() {
		return id;
	}
	
	public boolean isSelected() {
		return selected;
	}

 
    public RectangularShape getRs() {
		return rs;
	}

	public Node(String nom,RectangularShape rs) {
		this.rs = rs;
		this.name=nom;
		this.id=nom;
		this.port="0";
	}
	public void setNom(String nom) {
		this.name = nom;
		//this.id=nom;
	}


	public void moveBy(double dx, double dy) {
		double x = this.rs.getX();
		double y = this.rs.getY();
		double w = this.rs.getWidth();
		double h = this.rs.getHeight();
		this.rs.setFrame(x + dx, y + dy, w, h);
	}
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return name;
    }
    public String getName() {
		return name;
	}


	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
