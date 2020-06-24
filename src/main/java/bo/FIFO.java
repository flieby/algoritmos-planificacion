package bo;

import commons.CommonUtils;
import objects.*;

public class FIFO {

    public static String findavgTime(Work work){
        Result result = CommonUtils.toResult(work);

        result.getResult()[0].setWt(0);
        for(int i=1; i< work.getScenarios().size(); i++)
            result.getResult()[i].setWt(work.getScenarios().get(i-1).getEffort() + result.getResult()[i-1].getWt());

        for (int i = 0; i < work.getScenarios().size(); i++)
            result.getResult()[i].setFinishTime(work.getScenarios().get(i).getEffort() + result.getResult()[i].getWt());

        for (int i = 0; i < work.getScenarios().size(); i++) {
            result.getResult()[i].setProcess(work.getScenarios().get(i).getName());
            result.getResult()[i].setArrivalTime(work.getScenarios().get(i).getArrivalTime());
            result.getResult()[i].setEffort(work.getScenarios().get(i).getEffort());
            result.getResult()[i].setTurnAround(result.getResult()[i].getFinishTime()-work.getScenarios().get(i).getArrivalTime());
            result.getResult()[i].setTrTs(((float)result.getResult()[i].getFinishTime()-(float)work.getScenarios().get(i).getArrivalTime())/(float)work.getScenarios().get(i).getEffort());
        }

        return CommonUtils.printJSONResultString(new JSONFullResult(result));
    }
}
