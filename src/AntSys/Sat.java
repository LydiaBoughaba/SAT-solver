package AntSys;

import java.util.List;

public class Sat {
    // Parametres_SAT
	
	// nombre des Varriables
    public static int Nbr_litteraux = 75; 
    // nombre des Clauses
    public static int nbr_clause; 
    
    private List<Clause> clauses;

    public Sat(List<Clause> cls) {
    this.clauses=cls;
    }

    public List<Clause> getClauses() {
        return clauses;
    }

    public void setClauses(List<Clause> clauses) {
        this.clauses = clauses;
    }

    public int satisfiedClauses(int values[]) {
        int satisfactions = 0;
        for (Clause c : clauses) {
            for(Literal l : c.getLitterals()){
                if(l.getLitteralValue(values[l.getVar()-1]) == 1){
                    satisfactions ++;
                    break;
                }
            }
        }
        return satisfactions;
    }
    
    public boolean satisfied(Solution solution) {
        int[] values = solution.getValues();
        boolean sat = false;
        for (Clause c : clauses) {
            sat = false;
            for(Literal l : c.getLitterals()){
                if(l.getLitteralValue(values[l.getVar()-1]) == 1){
                    sat = true;
                }
            }
            if(!sat)
                return false;
        }
        return true;
    }
}
