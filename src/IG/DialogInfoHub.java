package IG;


public class DialogInfoHub {
	  private String nom, prtserver, Jseed,Jpanprobability, win;
	  private boolean configured=false;

	  public DialogInfoHub(){}
	  public DialogInfoHub(String nom, String prtserver, String Jseed, String Jpanprobability,String win,boolean b){

	    this.nom = nom;
	    this.Jseed = Jseed;
	    this.Jpanprobability = Jpanprobability;
	    this.win = win;
	    this.configured=b;
	    this.prtserver="0";
	  }

	  public boolean isConfigured() {
		return configured;
	}
	public String toString(){
	    String str;
	    if(this.nom != null && this.win != null && this.Jseed != null && this.Jpanprobability != null){
	      str = "Description de l'objet InfoZDialog";
	      str += "Nom : " + this.nom + "\n";
	      str += "Jseed : " + this.Jseed + "\n";
	      str += "Jpanprobability : " + this.Jpanprobability + "\n";
	      str += "win  : " + this.win + "\n";
	      str += "prtserver : " + this.prtserver + "\n";
	    }
	    else{
	      str = "Aucune information n'est enregistrée !";
	    }
	    return str;
	  }
	public String getNom() {
		return nom;
	}
	

	}