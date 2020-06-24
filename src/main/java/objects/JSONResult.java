package objects;

public class JSONResult {
    private String process;
    private Integer serviceTime;
    private Integer waitingTime;
    private Integer finishTime;
    private Integer turnAroundTime;
    private Integer arrivalTime;
    private Float trTs;
    private Integer temp;
    private Integer changeContextsCount = 0;

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getChangeContextsCount() {
        return changeContextsCount;
    }

    public void setChangeContextsCount(Integer changeContextsCount) {
        this.changeContextsCount = changeContextsCount;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(Integer turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public Float getTrTs() {
        return trTs;
    }

    public void setTrTs(Float trTs) {
        this.trTs = trTs;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
