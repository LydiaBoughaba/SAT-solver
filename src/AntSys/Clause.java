package AntSys;

import java.util.List;

public class Clause {

    private List<Literal> litterals;

    public Clause() {

    }

    public Clause(List<Literal> litterals) {
        this.litterals = litterals;
    }

    public List<Literal> getLitterals() {
        return litterals;
    }

    public void setLitterals(List<Literal> litterals) {
        this.litterals = litterals;
    }

}