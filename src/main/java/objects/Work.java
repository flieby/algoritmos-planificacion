package objects;

import java.util.ArrayList;

public class Work {
    private ArrayList<WorkLoad> scenarios = new ArrayList<>();

    public ArrayList<WorkLoad> getScenarios() {
        return scenarios;
    }

    public void setScenarios(ArrayList<WorkLoad> scenarios) {
        this.scenarios = scenarios;
    }
}
