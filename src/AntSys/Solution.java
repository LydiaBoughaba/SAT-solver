package AntSys;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

    private int[] values;
    private int lvl;
    private int index;
    private int satisfaction;

    public Solution(int[] values, int lvl, int value) {
        this.values = values.clone();
        this.lvl = lvl;
        this.values[lvl] = value;
    }

    public Solution(int[] values, int index, Sat sat, int n) {
        this.values = values.clone();
        this.index = index;
        if(this.values[index] == 0){
            this.values[index] = 1;
        } else {
            this.values[index] = 0;
        }
        this.satisfaction = sat.satisfiedClauses(this.getValues());
    }
    
    public Solution(int[] values, int index, Sat sat) {
        this.values = values.clone();
        this.satisfaction = sat.satisfiedClauses(this.getValues());
    }

    public int[] getValues() {
        return values;
    }

    public int getLvl() {
        return lvl;
    }

    public int getSatisfaction() {
        return satisfaction;
    }


    public void getKidsAStar(Stack<Solution> pile, int size, Sat sat) {
        List<Solution> sols = new ArrayList<Solution>();
        Solution sol1;
        for (int i = 0; i < size; i++) {
            if (i != index) {
                sol1 = new Solution(values, i, sat, 0);
                if (sols.isEmpty() && sol1.getSatisfaction() >= satisfaction) {
                    sols.add(sol1);
                } else if (sol1.getSatisfaction() >= satisfaction) {
                    for (int j = 0; j < sols.size(); j++) {
                        if (sol1.getSatisfaction() >= sols.get(j).getSatisfaction()) {
                            sols.add(j, sol1);
                            break;
                        }
                    }
                }
            }
        }
        for (int i = sols.size()-1; i >= 0; i--) {
            pile.push(sols.get(i));
        }
    }
}
