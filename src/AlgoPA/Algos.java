package AlgoPA;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Algos {

    private static final long DEFAULT_TIMEOUT = 2000 ; //en  Ms
    private static final String DFS_ALGORITHM = "Profondeur"; 
    private static final String A_STAR_ALGORITHM = "A*"; 

    private long debut, stopTime;
    private Solution Meilleure;

    private int M_sat;
    private int tmp_sats;

    private void printBestSolution(String algorithm) {
        System.out.println(algorithm + " Solution " + Arrays.toString(Meilleure.getValues()));
        System.out.println("nombre de clauses satisfaites : " + Load.sat.satisfiedClauses(Meilleure) + " - " + (float) Load.sat.satisfiedClauses(Meilleure) / Load.CLAUSE_NUMBER * 100 + "%");
        System.out.println("Temps d'execution  : " + (stopTime - debut) + "ms");
        System.out.println("******************************************");
    }
    
    // algorithme de recherche en profondeur D'abord
    
    public int depthFirstAlgorithm() {
    	//int cpt = 1;
        int[] values = new int[Load.VAR_NUM]; // initialisations des valeurs a 0
        Stack<Solution> sols = new Stack<Solution>(); 
        sols.push(new Solution(values, 0, 1));
        sols.push(new Solution(values, 0, 0)); 
        Solution s = sols.pop();

        Meilleure = s;  // initialisation de la meilleur solution

        M_sat = Load.sat.satisfiedClauses(Meilleure); // nombre de clauses satisfaites
        tmp_sats = 0;
        debut = System.currentTimeMillis(); 
        while (!Load.sat.satisfied(s) && (System.currentTimeMillis() - debut) < DEFAULT_TIMEOUT) {
            if (s.getLvl() < Load.VAR_NUM - 1) {
                s.getKidsProfondeur(sols); // generation des fils
                //cpt++;
            }
          // System.out.println("*\n+"+cpt+"\n*"); //pour compter le nombre de fils générér

            s = sols.pop(); // extraction du dernier element LIFO
            tmp_sats = Load.sat.satisfiedClauses(s); // Mis a jours de la meilleur solution
            if (tmp_sats > M_sat) {
                M_sat = tmp_sats;
                Meilleure = s;
            }
        }

        stopTime = System.currentTimeMillis();
        Load.DEPTH_FIRST_TOTAL_TIME += stopTime - debut; // ajout a global algo time
        System.out.print(M_sat);

		return M_sat;

    }

   

    public int aStarAlgorithm() {

        debut = System.currentTimeMillis();

        int[] valeurs = new int[Load.VAR_NUM];

        Solution s = new Solution(valeurs, -1, Load.sat); // creation de la solutions initial vecteur des 0
        Stack<Solution> pile = new Stack(); // intialisation de la pile

        List<Solution> Psolution = new ArrayList<>(); 

        boolean passed; 

        while (!Load.sat.satisfied(s) && (System.currentTimeMillis() - debut) < DEFAULT_TIMEOUT) {
            passed = false;
            for (Solution sol : Psolution) { // verifier si la solution has passed or not
                if (Arrays.equals(s.getValues(), sol.getValues())) {
                    passed = true;
                }
            }
            if (passed == true) {
                if (pile.empty()) {
                    break;
                }
                s = pile.pop();
            } 
            else { // si non developer des fils
                if (s.getLvl() < Load.VAR_NUM - 1) {
                    s.getKidsAStar(pile, Load.VAR_NUM, Load.sat);
                }
                Psolution.add(s); s = pile.pop();
            }
        }

        stopTime = System.currentTimeMillis();

        Meilleure = s;
        int so =  Load.sat.satisfiedClauses(Meilleure);
        Load.A_STAR_TOTAL_TIME += stopTime - debut;
        System.out.print(so);
        return so;

    }
    
    

}




