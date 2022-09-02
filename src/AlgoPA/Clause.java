package AlgoPA;
import java.util.List;

public class Clause {

    private List<Litteral> litterals;

    public Clause() {

    }

    public Clause(List<Litteral> litterals) {
        this.litterals = litterals;
    }

    public List<Litteral> getLitterals() {
        return litterals;
    }

    public void setLitterals(List<Litteral> litterals) {
        this.litterals = litterals;
    }


}

