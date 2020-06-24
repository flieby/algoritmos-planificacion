package objects;

public class Result {
    private SingleResult result[];
    private Float averageWT;
    private Float averageTR;
    private Integer changeContextCount = 0;

    public SingleResult[] getResult() {
        return result;
    }

    public Integer getChangeContextCount() {
        return changeContextCount;
    }

    public void setChangeContextCount(Integer changeContextCount) {
        this.changeContextCount = changeContextCount;
    }

    public void setResult(SingleResult[] result) {
        this.result = result;
    }

    public Float getAverageWT() {
        return averageWT;
    }

    public void setAverageWT(Float averageWT) {
        this.averageWT = averageWT;
    }

    public Float getAverageTR() {
        return averageTR;
    }

    public void setAverageTR(Float averageTR) {
        this.averageTR = averageTR;
    }
}
