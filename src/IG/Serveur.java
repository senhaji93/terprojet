package IG;

import java.awt.geom.RectangularShape;

import selective.repeat.Server;

public class Serveur extends Node {
	//private String nom;
	//private String id;
	public Serveur(String nom,RectangularShape rs) {
		super(nom,rs);
		//this.nom = nom;
		//this.id=nom;
	}

public void start() throws Exception {
	 Server s1=new Server(9879
     		,10
     		,100
     		,0.01);
     s1.run();
}

	public Serveur() {
		super();
	}
}
