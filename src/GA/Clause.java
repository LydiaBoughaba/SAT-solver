package GA;

import java.util.List;

public class Clause {
	
	private int taille;//le nombre de litteraux dans la clause
	private List<Litteral> litteraux;//la liste des litteraux dans la clause
	
	//Constructor
	public Clause(List<Litteral> litteraux) {
		this.taille = litteraux.size();
		this.litteraux = litteraux;
	}
	
	//Getters
	public int getTaille() {
		return taille;
	}
	
	public List<Litteral> getListLitteraux(){
		return litteraux;
	}
	
	//Vérifier si la clause est satisfaite
	public boolean isSatisfied() {
		boolean satisfied = false;
		
		for(Litteral litteral : litteraux) {
			if(litteral.getValue() == 1) {
				satisfied = true;
				break;
			}
		}
		
		return satisfied;
	}
}
