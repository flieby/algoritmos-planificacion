package objects;

public class SingleResult {

    private String process;
    private Integer ts;
    private Integer wt;
    private Integer finishTime;
    private Integer turnAround;
    private Float trTs;
    private Integer temps;
    private Integer completed;
    private Integer effort;
    private Integer priority;
    private Integer arrivalTime;
    private Integer flag = 0;
    //temp for effort
    private Integer k;
    //temp for arrival
    private Integer arr;

    public Integer getArr() {
        return arr;
    }

    public void setArr(Integer arr) {
        this.arr = arr;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public Integer getWt() {
        return wt;
    }

    public void setWt(Integer wt) {
        this.wt = wt;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getTurnAround() {
        return turnAround;
    }

    public void setTurnAround(Integer turnAround) {
        this.turnAround = turnAround;
    }

    public Float getTrTs() {
        return trTs;
    }

    public void setTrTs(Float trTs) {
        this.trTs = trTs;
    }

    public Integer getTemps() {
        return temps;
    }

    public void setTemps(Integer temps) {
        this.temps = temps;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }
}
