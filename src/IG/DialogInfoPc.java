package IG;


public class DialogInfoPc {
	  private String nom, prclient, prserver, nomfichierD, nomfichierR;
	  private boolean configured=false;

	  public DialogInfoPc(){}

	  public DialogInfoPc(String nom, String prclient, String prserver, String nomfichierD, String nomfichierR,boolean b){
	    this.nom = nom;
	    this.prclient = prclient;
	    this.prserver = prserver;
	    this.nomfichierD = nomfichierD;
	    this.nomfichierR = nomfichierR;
	    this.configured=b;
	  }

	  public boolean isConfigured() {
		return configured;
	}
	public String toString(){
	    String str;
	    if(this.nom != null && this.prclient != null && this.nomfichierR != null && this.prserver != null && this.nomfichierD != null){
	      str = "Description d'equipement";
	      str += "\nNom : " + this.nom + "\n";
	      str += "prclient : " + this.prclient + "\n";
	      str += "prserver : " + this.prserver + "\n";
	      str += "nomfichierD : " + this.nomfichierD + "\n";
	      str += "nomfichierR : " + this.nomfichierR + "\n";
	    }
	    else{
	      str = "Aucune information !";
	    }
	    return str;
	  }
	public String getNom() {
		return nom;
	}
	

	}