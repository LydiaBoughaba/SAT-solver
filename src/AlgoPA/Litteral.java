package AlgoPA;


public class Litteral {

    private int var;
    private int value;

    public Litteral(int var, int value) {
        this.var = var;
        this.value = value;
    }

    public int getVar() {
        return var;
    }

    public void setVar(int var) {
        this.var = var;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getLitteralValue(int val) {
        if (this.value == 1) {
            return val;
        } else {
            if (val == 1) {
                return 0;
            } else {
                return 1;
            }
        }
    }
    public int getLitteralVar() {
        if (this.value == 1) {
            return var;
        }
        return -var;
    }

}
