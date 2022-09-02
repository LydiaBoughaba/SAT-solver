package GA;

import java.util.concurrent.ThreadLocalRandom;

public class Solution {
	
	private int solution[];
	private int size = Main.numLitteraux;
	private int fitness;
	
	//Générer des solutions aléatoires 
	public Solution() {
		solution = new int[size];
		for(int i=0;i<size;i++) {
			solution[i] = ThreadLocalRandom.current().nextInt(0, 1 + 1);
		}
		setFitness(Main.sat.numberSatSatisfied(solution));
		//à changer après
		//setFitness(ThreadLocalRandom.current().nextInt(0, 325 + 1));
	}
	
	public Solution(int[] values) {
        this.solution = values;
        setFitness(Main.sat.numberSatSatisfied(solution));
      	//à changer après
      	//setFitness(ThreadLocalRandom.current().nextInt(0, 325 + 1));
    }
	
	public double fitnessPourcentage(int total) {
		return fitness/total;
	}
	
	//Getters and Setters
	public int[] getSolution() {
		return solution;
	}
	
	public int getSize() {
		return size;
	}

	public void setSolution(int solution[]) {
		this.solution = solution;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
}
