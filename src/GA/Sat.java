package GA;

import java.util.List;

public class Sat {
	private int size;//le nombre de clauses dans SAT
	private List<Clause> clauses;// la liste des clauses dans SAT
	
	//Constructor
	public Sat() {
		
	}
	public Sat(List<Clause> clauses) {
		this.size = clauses.size();
		this.clauses = clauses;
	}
	
	//Getters
	public List<Clause> getListClauses(){
		return clauses;
	}
	
	public int getNumberOfClauses() {
		return size;
	}
	
	//Vérifier si une instance va satisfaire toutes les clauses
	public boolean isSatSatisfied(int[] values) {
		boolean satisfied = false;
		
		for(Clause c: clauses) {
			satisfied = false;
			for(Litteral l: c.getListLitteraux()) {
				if(l.getValue(values[l.getNumber()-1]) == 1){
					satisfied = true;
					break;
				}
			}
			if(satisfied == false) {
				return satisfied;
			}
		}
		
		return satisfied;
	}
	
	//Avoir le nombre de clauses satisfaites
	public int numberSatSatisfied(int[] values) {
		int nbrSatisfied = 0;
		
		for(Clause c: clauses) {
			for(Litteral l: c.getListLitteraux()) {
				if(l.getValue(values[l.getNumber()-1]) == 1){
					nbrSatisfied++;
					break;
				}
			}
		}
		
		return nbrSatisfied;
	}
}
