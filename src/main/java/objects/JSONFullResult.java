package objects;

import java.util.ArrayList;

public class JSONFullResult {
    private ArrayList<JSONResult> results = new ArrayList<>();
    private float averageTrTs;
    private float averageTr;
    private Integer changeContextCount = 0;

    public JSONFullResult(Result result) {
        float totalTRTS = 0;
        float totalTR = 0;
        for(int i=0; i<result.getResult().length;i++){
            JSONResult res = new JSONResult();
            res.setProcess(result.getResult()[i].getProcess());
            res.setServiceTime(result.getResult()[i].getEffort());
            res.setWaitingTime(result.getResult()[i].getWt());
            res.setFinishTime(result.getResult()[i].getFinishTime());
            res.setTurnAroundTime(result.getResult()[i].getTurnAround());
            res.setArrivalTime(result.getResult()[i].getArrivalTime());
            res.setTrTs(result.getResult()[i].getTrTs());
            totalTR+=result.getResult()[i].getTurnAround();
            totalTRTS+=result.getResult()[i].getTrTs();
            this.getResults().add(res);
        }
        this.setChangeContextCount(result.getChangeContextCount());
        this.setAverageTrTs(totalTRTS/result.getResult().length);
        this.setAverageTr(totalTR/result.getResult().length);
    }

    public Integer getChangeContextCount() {
        return changeContextCount;
    }

    public void setChangeContextCount(Integer changeContextCount) {
        this.changeContextCount = changeContextCount;
    }

    public ArrayList<JSONResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<JSONResult> results) {
        this.results = results;
    }

    public float getAverageTr() {
        return averageTr;
    }

    public void setAverageTr(float averageTr) {
        this.averageTr = averageTr;
    }

    public float getAverageTrTs() {
        return averageTrTs;
    }

    public void setAverageTrTs(float averageTrTs) {
        this.averageTrTs = averageTrTs;
    }
}
