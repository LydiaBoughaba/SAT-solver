package AlgoPA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Solution {

    private int[] values;
    private int lvl;
    private int index;
    private int Satisf;

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
        this.Satisf = sat.satisfiedClauses(this);
    }
    
    public Solution(int[] values, int index, Sat sat) {
        this.values = values.clone();
        this.Satisf = sat.satisfiedClauses(this);
    }

    public int[] getValues() {
        return values;
    }

    public int getLvl() {
        return lvl;
    }

    public int getSatisfaction() {
        return Satisf;
    }

    public void getKidsProfondeur(Stack<Solution> pile) {
        pile.push(new Solution(values, lvl + 1, 1));
        pile.push(new Solution(values, lvl + 1, 0));
    }

    public void getKidsLargeur(LinkedList<Solution> fifo) throws OutOfMemoryError {
        fifo.addLast(new Solution(values, lvl + 1, 0));
        fifo.addLast(new Solution(values, lvl + 1, 1));
    }

    public void getKidsAStar(Stack<Solution> pile, int size, Sat sat) {
        List<Solution> sols = new ArrayList<Solution>();
        Solution sol;  
        for (int i = 0; i < size; i++) {
            if (i != index) {
                sol = new Solution(values, i, sat, 0);
                if (sols.isEmpty() && sol.getSatisfaction() >= Satisf) { sols.add(sol);
                } else if (sol.getSatisfaction() >= Satisf) {
                    for (int j = 0; j < sols.size(); j++) {
                        if (sol.getSatisfaction() >= sols.get(j).getSatisfaction()) {
                            sols.add(j, sol); break;
                        } }}} }
        for (int i = sols.size()-1; i >= 0; i--) {
            pile.push(sols.get(i));
        }
    }
}
