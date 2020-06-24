package bo;

import commons.CommonUtils;
import objects.JSONFullResult;
import objects.Result;
import objects.Work;

public class RR {

    public static String printRR(Work work, int time_quantum, boolean contextChanges){
        int limit = work.getScenarios().size();
        int x = work.getScenarios().size();
        int counter = 0;
        int total = 0;
        int i;
        int changeContext = 1;
        Integer changeContextCounter = 0;

        Result result = CommonUtils.toResult(work);
        String actualContext = result.getResult()[0].getProcess();
        for(total = 0, i = 0; x != 0;)
        {

            if(contextChanges){
                if(changeContext==time_quantum){
                    total++;
                    changeContext=1;
                    changeContextCounter++;
                }
            }

            //si el esfuerzo restante es menor o igual al quantum y mayor a 0
            if(result.getResult()[i].getK()<=time_quantum && result.getResult()[i].getK()>0){
                total = total + result.getResult()[i].getK();
                result.getResult()[i].setK(0);
                counter = 1;
            } //si el esfuerzo restante es mayor a 0 pero mayor al quantum
            else if(result.getResult()[i].getK() > 0) {
                result.getResult()[i].setK(result.getResult()[i].getK() - time_quantum);
                total = total + time_quantum;
            }//si el esfuerzo restante es 0 y counter 1
            if(result.getResult()[i].getK() == 0 && counter == 1) {
                x--;
                result.getResult()[i].setTurnAround(total-result.getResult()[i].getArrivalTime());
                result.getResult()[i].setWt(total-result.getResult()[i].getArrivalTime()-result.getResult()[i].getEffort());
                result.getResult()[i].setFinishTime(total);
                result.getResult()[i].setTrTs((((float)result.getResult()[i].getFinishTime()-(float)result.getResult()[i].getArrivalTime())/(float)result.getResult()[i].getEffort()));

                counter = 0;
            }
            if(i == limit - 1)
                i = 0;
            else if(result.getResult()[i+1].getArrivalTime() <= total)
                i++;
            else
                i = 0;
        }

        if(contextChanges)
            result.setChangeContextCount(changeContextCounter);
        return CommonUtils.printJSONResultString(new JSONFullResult(result));


    }

}
