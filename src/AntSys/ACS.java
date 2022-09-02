
package AntSys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class ACS {

    public static List<Clause> clauses;
    public static List<Literal> litterals;
    public static Literal litteral;
    public static Clause clause;
    public static Sat sat;

    // Parametres_A*
    private static final long DEFAULT_TIMEOUT = 10000; 
    private static long startTime;
    private static Solution best;
 
   public static  FileWriter fw; 
/*
    // Parametres_ACS
    public static  double ALPHA = 0.9; 
    public static  double BETA = 0.9; 
    public static  double V_RATE = 0.1; 
    public static  double I_PHEROMON = 0.1; 
    public static  int MAX_ITERATIONS= 1000; 
    public static  int ANT_COUNT = 10; 
    public static  double Q_0 = 0.01; 
  */
    public static long ACS_TOTAL_TIME = 0; 
    public static int[][] FITNESS; // matrice_contiens_le_nombre_des_clauses_satisfiable_pour_chaque_literal

    public static List<Ant> ants; // colonie 
    public static double[][] pheromon; //table_de_pheromone

   public static Affichage aff;

    // Chargement_d'un_fichier
    public static Sat LoadSat(String fileName) {

      
        clauses = new ArrayList<>();

        int tmpParseInt;

        try {
            InputStream is = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            String[] lit;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("c") || line.startsWith("%") || line.startsWith("0") || line.equals("")) {
                } else {
                    if (line.startsWith("p")) {
                        line = line.replace("p cnf ", "");
                        line = line.replaceFirst(" ", "");
                        lit = line.split(" ");
                        Sat.Nbr_litteraux= Integer.parseInt(lit[0]);
                        Sat.nbr_clause = Integer.parseInt(lit[1]);
                        FITNESS = new int[Sat.Nbr_litteraux][2];
                        continue;
                    }
                    litterals = new ArrayList<Literal>();
                    if (first) {
                        line = line.replaceFirst(" ", "");
                        first = false;
                    }
                    lit = line.split(" ");
                    for (int i = 0; i < 3; i++) {
                        tmpParseInt = Integer.parseInt(lit[i]);
                        if (tmpParseInt > 0) {
                            FITNESS[tmpParseInt - 1][1]++;
                            litteral = new Literal(tmpParseInt, 1);
                        } else {
                            FITNESS[-(tmpParseInt) - 1][0]++;
                            litteral = new Literal(-tmpParseInt, -1);
                        }
                        litterals.add(litteral);
                    }
                    clause = new Clause(litterals);
                    clauses.add(clause);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new Sat(clauses);

    }
    
    public static <T> Affichage AC_S(String ch,Parametre p) throws IOException {
    	aff=new Affichage();
    	//instancier_sat   
    	
        sat = LoadSat(ch); 
        
        //declaration_de_la_matrice_de_pheromone
        pheromon = new double[Sat.Nbr_litteraux][2]; 
        //initialisation_de_pheromone
        for (int i = 0; i < Sat.Nbr_litteraux; i++) { 
            pheromon[i][0] = pheromon[i][1] = p.getI_PHEROMON();
        }
        
        //lancement_de_A*
        Solution sol=aStarAlgorithm();
        
        // start_time
        ACS_TOTAL_TIME = System.currentTimeMillis(); 
        
        // intialisation_de_la_pile
        Stack<Integer> pile = new Stack<>();
        
        //insere_best_dans_la_pile
       
        int b=(int) Math.random();
        pile.add(b);
        //fichier_text 
        
        //Génération
        for (int i = 0; i < p.getMAX_ITERATIONS(); i++) { 
        	// création_de_la_colonie  
            ants = new ArrayList<>(); 
        
            for (int j = 0; j < p.getANT_COUNT(); j++) {    
            	//mettre_la_solution_de_A_dans_le_constructeurs
                // ajout_des_nouvelles_fourmis
                ants.add(new Ant(sol.getValues())); 
            }
            int best=0;
            Ant bestAnt = null;
            
            for (Ant ant : ants) {
            	// contruction_d'une_solution
                ant.build();
                
                // f(best)>f(s) 
                if (ant.getFitness() > best) {
                	// mis_a_jours_de_la_meilleur_fourmis
                	bestAnt = ant; 
                    best = ant.getFitness();      
                }
            }
         // mise_a_jour_offline
            for (int j = 0; j < Sat.Nbr_litteraux; j++) { 
                if (bestAnt.getValues()[j] == 0) {
                    pheromon[j][0] = (1 - p.getV_RATE()) * pheromon[j][0] + p.getV_RATE() * (double) (FITNESS[j][0]);
                    pheromon[j][1] = (1 - p.getV_RATE()) * pheromon[j][1];
                } else {
                    pheromon[j][0] = (1 - p.getV_RATE()) * pheromon[j][0];
                    pheromon[j][1] = (1 - p.getV_RATE()) * pheromon[j][1] + p.getV_RATE() * (double) (FITNESS[j][1]);
                }
            }
            //sauvegarde_des_resultats
            int worst=0;
            worst=pile.lastElement();
            if(best>worst) {
            	if (pile.empty()) {
                   pile.removeElement(worst);
                }
            	pile.add(best);
            }
             
            if (bestAnt.getFitness()==325) { 
            	System.out.print("Iteration " + i + " : "+bestAnt.getFitness());
            	break;
            }   
          
        }  
        aff.setBest(""+Collections.max(pile));
        aff.setTemps((System.currentTimeMillis()-ACS_TOTAL_TIME)+" ms");
    	
    	 return aff;
    }

    //generation_de_solution_avec_A*
    public static Solution aStarAlgorithm() {

        startTime = System.currentTimeMillis();

        int[] values = new int[Sat.Nbr_litteraux];

        Solution s = new Solution(values, -1, ACS.sat); 
        Stack<Solution> pile = new Stack<>(); 

        List<Solution> passedSolutions = new ArrayList<>(); 

        boolean passed; 

        while (!ACS.sat.satisfied(s) && (System.currentTimeMillis() - startTime) < DEFAULT_TIMEOUT) {
            passed = false;
            for (Solution sol : passedSolutions) { 
                if (Arrays.equals(s.getValues(), sol.getValues())) {
                    passed = true;
                }
            }
            if (passed == true) {  
                if (pile.empty()) {
                    break;
                }
                s = pile.pop();
            } else { 
                if (s.getLvl() < Sat.Nbr_litteraux- 1) {
                    s.getKidsAStar(pile, Sat.Nbr_litteraux, ACS.sat);
                }
                passedSolutions.add(s);
                s = pile.pop();
            }
        }

        best = s;
        return best;
    }
}