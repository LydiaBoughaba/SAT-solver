package GA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Population {
	
	private List<Solution> solutions;
	
	//Initialisation de la population
	public Population() {
		solutions = new ArrayList<>();
		
		for(int i=0;i<Main.populationSize;i++) {
			solutions.add(new Solution());
		}
	}
	
	//La meilleure solution de toute la population
	public Solution getBestSolution() {
		Solution sBest = Collections.max(this.solutions,new SolutionCompare());
		return sBest;
	}
	
	//La meilleure fitness de toute la population
	public int getBestFitness() {
		Solution sBest = Collections.max(this.solutions,new SolutionCompare());
		return sBest.getFitness();
	}
	
	//Getters and Setters
	public List<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}
}
