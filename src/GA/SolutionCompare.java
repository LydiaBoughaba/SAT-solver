package GA;

import java.util.Comparator;

public class SolutionCompare implements Comparator<Solution>{

	@Override
	public int compare(Solution o1, Solution o2) {
		// TODO Auto-generated method stub
		if (o1.getFitness() == o2.getFitness()) {
            return 0;
        } else if (o1.getFitness() < o2.getFitness()) {
            return -1;
        } else if (o1.getFitness() > o2.getFitness()) {
            return 1;
        }
        return 0;
	}

}
