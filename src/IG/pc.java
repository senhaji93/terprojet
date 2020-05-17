package IG;

import java.awt.geom.RectangularShape;


import java.util.List;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.Port;
import javax.swing.JFileChooser;





public class pc extends Node{
//private String nom;
//private String id;
	private String ipAdresse;
	private String ip;
	private String ip_destination;
	private String passerelle;
	private String masque;
	
	
	public pc(String nom,int nombre_port,List<Port> ports,Float horizontal_position,
			Float vertival_position,String ip,String ip_destination,
			String passerelle) {
		super();
		this.nom=nom;
		this.nombre_port=nombre_port;
		this.ip=ip;
		this.ports=ports;
		this.horizontal_position=horizontal_position;
		this.vertival_position=vertival_position;
		this.ip_destination=ip_destination;
		this.passerelle=passerelle;
		
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp_destination() {
		return ip_destination;
	}

	public void setIp_destination(String ip_destination) {
		this.ip_destination = ip_destination;
	}

	
	

	public String getPasserelle() {
		return passerelle;
	}

	public void setPasserelle(String passerelle) {
		this.passerelle = passerelle;
	}

	public String getMasque() {
		return masque;
	}

	public void setMasque(String masque) {
		this.masque = masque;
	}

	

	

public String getIpAdresse() {
		return ipAdresse;
	}

	public void setIpAdresse(String ipAdresse) {
		this.ipAdresse = ipAdresse;
	}

public pc(String nom,RectangularShape rs) {
	super(nom,rs);
	//this.nom = nom;
	//this.id=nom;
}

/*public String getId() {
	return id;
}
public String getName() {
	return nom;
}


public void setNom(String nom) {
	super.setNom(nom);
}*/
public pc() {
	super();

}

}
