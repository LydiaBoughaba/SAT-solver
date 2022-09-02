package AlgoPA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Interface.PanelAlgoAveugleHeuri;

public class Load {

    public static List<Clause> clauses;
    public static List<Litteral> litterals;
    public static Litteral litteral;
    public static Clause clause;
    public static  Sat sat;
    public static Solution B;

    // Parametres
    public static int VAR_NUM; // nombre des Varriables
    public static int CLAUSE_NUMBER; 

    public static long DEPTH_FIRST_TOTAL_TIME = 0;
    public static long BREADTH_FIRST_TOTAL_TIME = 0;
    public static long A_STAR_TOTAL_TIME = 0;

    
    
    public static void exec() {
    	Algos algos = new Algos();
    	PanelAlgoAveugleHeuri panel = new PanelAlgoAveugleHeuri();
       // System.out.print("\n Algo Profondeur \n");
    	
       // sat = LoadSat("/res/uuf75-325/uuf75-01.cnf");
       /* for (int x = 1; x <= 1; x++) {
            //System.out.println("Instance " + x);
        	
            sat = LoadSat("/res/uuf75-325/uuf75-01.cnf");
            algos.depthFirstAlgorithm();
        }*/
       // System.out.print("\n\n Algo A * ");

       /* for (int x = 1; x <= 1; x++) {
            //System.out.println("\nInstance " + x);
        	
            sat = LoadSat("/res/uuf75-325/uuf75-01.cnf");
            algos.aStarAlgorithm();
        }*/

    }
    
    // Chargement d'un fichier
    public static Sat LoadSat(String fileName) {

        //Initilisation des Listes
        clauses = new ArrayList<>();

        //Juste une varriable tmp
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
                    if(line.startsWith("p")){
                        line = line.replace("p cnf ", "");
                        line = line.replaceFirst(" ", "");
                        lit = line.split(" ");
                        VAR_NUM = Integer.parseInt(lit[0]);
                        CLAUSE_NUMBER = Integer.parseInt(lit[1]);
                        continue;
                    }
                    litterals = new ArrayList<Litteral>();
                    if (first) {
                        line = line.replaceFirst(" ", "");
                        first = false;
                    }
                    lit = line.split(" ");
                    for (int i = 0; i < 3; i++) {
                        tmpParseInt = Integer.parseInt(lit[i]);
                        if (tmpParseInt > 0) {
                            litteral = new Litteral(tmpParseInt, 1);
                        } else {
                            litteral = new Litteral(-tmpParseInt, -1);
                        }
                        litterals.add(litteral);
                    }
                    clause = new Clause(litterals);
                    clauses.add(clause);
                }
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.toString());
        }

        return new Sat(clauses);
    }

}
