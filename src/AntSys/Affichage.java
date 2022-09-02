package AntSys;

public class Affichage {
 int nomfichier;
 String temps;
 String best;  
 public Affichage(int nomfichier, String temps, String best) {
	super();
	this.nomfichier = nomfichier;
	this.temps = temps;
	this.best = best;
}
public Affichage() {
	// TODO Auto-generated constructor stub
}

public String getTemps() {
	return temps;
}
public void setTemps(String temps) {
	this.temps = temps;
}
public String getBest() {
	return best;
}
public void setBest(String best) {
	this.best = best;
}
 
}
