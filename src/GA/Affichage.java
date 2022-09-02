package GA;

public class Affichage {
	 private int nomfichier;
	 private String temps;
	 private String best;
	 private String ram;
	 public Affichage(int nomfichier, String temps, String best,String ram) {
		super();
		this.nomfichier = nomfichier;
		this.temps = temps;
		this.best = best;
		this.ram = ram;
	}
	public Affichage() {
		// TODO Auto-generated constructor stub
	}

	public String getTemps() {
		return temps;
	}
	
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
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
