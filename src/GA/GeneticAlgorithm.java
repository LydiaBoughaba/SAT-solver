package GA;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithm {
	private Population population;
	
	public GeneticAlgorithm() {
		setPopulation(new Population());
	}
	
	//Selection parents from population randomly
	public List<Solution> selectParents() {
		List<Solution> parents = new ArrayList<>();
		for(int i=0;i<Main.selectionSize;i++) {
			Solution parent = population.getSolutions().get(ThreadLocalRandom.current().nextInt(0, Main.populationSize));
			if(parents.contains(parent)) {
				i--;
			}else {
				parents.add(parent);
			}
		}
		return parents;
	}
	
	//Selection parents from the population with the wheel
	public List<Solution> selectParentsWheel() {
		List<Solution> parents = new ArrayList<>();
		int total = 0;
		for(int i=0;i<Main.populationSize;i++) {
			total = total + population.getSolutions().get(i).getFitness();
		}
		
		this.population.getSolutions().sort(new SolutionCompare());
		double[] probs = new double[this.population.getSolutions().size()];
		
		for(int i=0;i<this.population.getSolutions().size();i++) {
			probs[i] = this.fitnessPourcentage(this.population.getSolutions().get(i).getFitness());
		}
		
		for(int i=0;i<Main.selectionSize;i++) {
			double random = ThreadLocalRandom.current().nextDouble(0, 1);
			int position = 0;
			for(int j=0;j<this.population.getSolutions().size();j++) {
				if(random<probs[j]) {
					position = j;
					break;
				}
			}
			Solution parent = this.population.getSolutions().get(position);
			if(parents.contains(parent)) {
				i--;
			}else {
				parents.add(parent);
			}
		}
		
		return parents;
	}
	
	//Crossover between 2 parents
	public Solution crossOverParents(Solution parent1,Solution parent2){
		int[] values = new int[Main.numLitteraux];
		for(int i=0;i<Main.numLitteraux;i++) {
			if(Math.random()<0.5) {
				values[i] = parent1.getSolution()[i];
			}else {
				values[i] = parent2.getSolution()[i];
			}
		}
		return new Solution(values);
	}
	
	//Crossover
	public void crossOver() {
		Solution parent1,parent2;
		Population p1 = new Population();
		Population p2 = new Population();
		List<Solution> newPopulation = new ArrayList<>();
		for(int i=0;i<Main.populationSize;i++) {
			if(Math.random()<Main.crossOverChance) {
				p1.setSolutions(this.selectParents());
				p2.setSolutions(this.selectParents());
				parent1 = p1.getBestSolution();
				parent2 = p2.getBestSolution();
				newPopulation.add(this.crossOverParents(parent1, parent2));
			}else {
				newPopulation.add(this.population.getSolutions().get(i));
			}
		}
		this.population.setSolutions(newPopulation);
	}
	
	public void crossOverWheel() {
		Solution parent1,parent2;
		Population p1 = new Population();
		Population p2 = new Population();
		List<Solution> newPopulation = new ArrayList<>();
		for(int i=0;i<Main.populationSize;i++) {
			if(Math.random()<Main.crossOverChance) {
				p1.setSolutions(this.selectParentsWheel());
				p2.setSolutions(this.selectParentsWheel());
				parent1 = p1.getBestSolution();
				parent2 = p2.getBestSolution();
				newPopulation.add(this.crossOverParents(parent1, parent2));
			}else {
				newPopulation.add(this.population.getSolutions().get(i));
			}
		}
		this.population.setSolutions(newPopulation);
	}
	
	//Mutation
	public void mutation() {
		List<Solution> newPopulation = new ArrayList<>();
		for(int i=0;i<Main.populationSize;i++) {
			newPopulation.add(this.mutationSolution(this.population.getSolutions().get(i)));
		}
		this.population.setSolutions(newPopulation);
	}
	
	//Mutation of a solution
	public Solution mutationSolution(Solution s) {
		int[] values = new int[Main.numLitteraux];
		for(int i=0;i<s.getSize();i++) {
			if(Math.random()<Main.mutationChance) {
				if(s.getSolution()[i] == 1) {
					values[i] = 0;
				}else {
					values[i] = 1;
				}
			}else {
				values[i] = s.getSolution()[i];
			}
		}
		return new Solution(values);
	}
	
	//Get the fitnessPourcentage 
	public double fitnessPourcentage(int fitness) {
		int total = 0;
		for(int i=0;i<Main.populationSize;i++) {
			total = total + population.getSolutions().get(i).getFitness();
		}
		double fitnessPourcentage = fitness / total;
		
		return fitnessPourcentage;
	}
	
	//Print the best solution from the population
	public void printBestSolution() {
		String values ="[";
		for(int i=1;i<=Main.numLitteraux;i++) {
			values = values +"X"+i+" = ";
			values = values + this.population.getBestSolution().getSolution()[i-1];
			if(i<Main.numLitteraux) {
				values = values + " , ";
			}
		}
		values = values + " ]";
		System.out.println("Solution de l'instance : " +values);
	}
	
	//Getters and Setters
	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}

}
