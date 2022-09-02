package GA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static Sat sat;
	public static Clause clause;
	public static Litteral litteral;
	public static List<Clause> clauses;
	public static List<Litteral> litteraux;
	public static int numClauses = 325;
	public static int numLitteraux = 75;
	public static int populationSize = 60;
	public static double mutationChance = 0.05; 
    public static double crossOverChance = 0.9;
    public static int selectionSize = 40;
    public static long startTime;
    public static final int timeMax = 150000;
    public static long totalTime = 0;
    public static long ramAll=0;
    public static Affichage aff;
    
	//Load the bunchemark
	public static Sat loadFile(String nameFile) {
		
		clauses = new ArrayList<>();
		int tmp;
		
		try {

            InputStream is = new FileInputStream(nameFile);
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
                        numLitteraux = Integer.parseInt(lit[0]);
                        numClauses = Integer.parseInt(lit[1]);
                        continue;
                    }
                    litteraux = new ArrayList<Litteral>();
                    if (first) {
                        line = line.replaceFirst(" ", "");
                        first = false;
                    }
                    lit = line.split(" ");
                    for (int i = 0; i < 3; i++) {
                        tmp = Integer.parseInt(lit[i]);
                        if (tmp > 0) {
                            litteral = new Litteral(tmp, 1);
                        } else {
                            litteral = new Litteral(-tmp, -1);
                        }
                        litteraux.add(litteral);
                    }
                    clause = new Clause(litteraux);
                    clauses.add(clause);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
		
		Sat s = new Sat(clauses);
		
		return s;
	}
	
	public static <T>Affichage GA(String file) {
		aff=new Affichage();
		sat = loadFile(file);
		
		GeneticAlgorithm GA;
		GA = new GeneticAlgorithm();
		startTime = System.currentTimeMillis();
		while(GA.getPopulation().getBestFitness() < Main.numClauses && System.currentTimeMillis() - startTime < timeMax) {
			GA.crossOver();
			GA.mutation();
		}
		//Calcul de l'utilisation de la RAM
		Runtime rt = Runtime.getRuntime();
		long ramMax = rt.maxMemory();
		long ram = rt.totalMemory() - rt.freeMemory();
		totalTime += System.currentTimeMillis() - startTime;
		ramAll = ramAll + ram/(1024L * 1024L);
		
		aff.setRam(""+(ram*100.0)/ramMax+"%");
		aff.setBest(""+GA.getPopulation().getBestFitness());
        aff.setTemps((System.currentTimeMillis() - startTime)+" ms");
		return aff;
	}
}
