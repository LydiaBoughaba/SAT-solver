package AntSys;



public class Ant {
	
	// solution
    private int[] values; 
    
    // nombre de clauses satisfaites
    private int fitness; 

    // initialisation par une solution De A*
    public Ant(int[]s) { 
       values = s;   
    }
    
    // construction_de_la_solution
    public void build() { 
        for (int i = 0; i < Sat.Nbr_litteraux; i++) {
        	// regles_de_transition
        	rules(i); 
            // mise_a_jour_en_ligne
            ACS.pheromon[i][values[i]] = ACS.pheromon[i][values[i]] * (1 - Parametre.V_RATE) + Parametre.V_RATE *Parametre.I_PHEROMON;
            }
            // evaluation_de_la_solution_d'une_fourmis
            this.fitness = ACS.sat.satisfiedClauses(values); 
    }
    // regles_de_transition
    public void rules(int rules) {

        double k[] = new double[2];
        double m[] = new double[2];
        double proba;
        double sum = 0;
        // calcule_des_probas
        for (int i = 0; i < 2; i++) { 
            k[i] = Math.pow(ACS.pheromon[rules][i], Parametre.ALPHA);
            m[i] = Math.pow(ACS.FITNESS[rules][i], Parametre.BETA);
            sum += k[i] * m[i];
        }

        proba = k[0] * m[0] / sum;
       // parametre_Q0
        if (Math.random() > Parametre.Q_0) { 
        	// suivre_the_best
            if (Math.random() <= proba) { 
                values[rules] = 0;
            } else {
                values[rules] = 1;
            }
        } else {
        	// choissir_x0_ou_x1
            if(proba >= 0.5){ 
                values[rules] = 0;
            } else {
                values[rules] = 1;
            }
        }

    }

    public int[] getValues() {
        return values;
    }

    public int getFitness() {
        return fitness;
    }

}
