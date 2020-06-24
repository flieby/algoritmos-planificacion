package bo;
import commons.CommonUtils;
import objects.*;

public class SJF {

        public static String printPreemptive(Work work, boolean withContextChange){
            Result result = CommonUtils.toResult(work);
            int n= work.getScenarios().size();
            int i, st=0, tot=0;
            String actualProcess = result.getResult()[0].getProcess();
            Integer changeCounter = 0;


            while(true){

                int min=999999,c=n;
                if (tot==n)
                    break;


                for ( i=0;i<n;i++) {
                    if(result.getResult()[i].getArrivalTime()<=st && result.getResult()[i].getFlag()==0 && result.getResult()[i].getK()<min) {
                        min=result.getResult()[i].getK();
                        c=i;
                    }
                }

                if(withContextChange)
                    if(!actualProcess.equals(result.getResult()[c].getProcess())){
                        st++;
                        changeCounter++;
                    }

                if (c==n)
                    st++;
                else {
                    result.getResult()[c].setK(result.getResult()[c].getK()-1);
                    st++;
                    if (result.getResult()[c].getK()==0)
                    {
                        result.getResult()[c].setCompleted(st);
                        result.getResult()[c].setFlag(1);
                        tot++;
                    }
                }
                if(withContextChange)
                    actualProcess = result.getResult()[c].getProcess();
            }

            if(withContextChange)
                result.setChangeContextCount(changeCounter);

            for(i=0;i<n;i++) {
                result.getResult()[i].setFinishTime(result.getResult()[i].getCompleted());
                result.getResult()[i].setTurnAround(result.getResult()[i].getCompleted() - result.getResult()[i].getArrivalTime());
                result.getResult()[i].setWt(result.getResult()[i].getTurnAround() - result.getResult()[i].getEffort());
                result.getResult()[i].setTrTs((((float)result.getResult()[i].getFinishTime()-(float)result.getResult()[i].getArrivalTime())/(float)result.getResult()[i].getEffort()));
            }

            return CommonUtils.printJSONResultString(new JSONFullResult(result));
        }

        public static String printNonPreemptive(Work work) {
            Result result = CommonUtils.toResult(work);
            int st=0, tot=0;

            while(true)
            {
                Integer canUpdate=work.getScenarios().size();
                Integer min=999999;
                if (tot == work.getScenarios().size()) // total no of process = completed process loop will be terminated
                    break;

                for (int i=0; i<work.getScenarios().size(); i++){
                    if(work.getScenarios().get(i).getArrivalTime()<=st && result.getResult()[i].getFlag()==0 && work.getScenarios().get(i).getEffort()<min){
                        min=work.getScenarios().get(i).getEffort();
                        canUpdate=i;
                    }
                }

                /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
                if (canUpdate==work.getScenarios().size())
                    st++;
                else
                {
                    result.getResult()[canUpdate].setCompleted(st+result.getResult()[canUpdate].getEffort());
                    st+=result.getResult()[canUpdate].getEffort();
                    result.getResult()[canUpdate].setTurnAround(result.getResult()[canUpdate].getCompleted()-result.getResult()[canUpdate].getArrivalTime());
                    result.getResult()[canUpdate].setWt(result.getResult()[canUpdate].getTurnAround()-result.getResult()[canUpdate].getEffort());
                    result.getResult()[canUpdate].setFinishTime(result.getResult()[canUpdate].getCompleted());
                    result.getResult()[canUpdate].setTrTs((((float)result.getResult()[canUpdate].getFinishTime()-(float)result.getResult()[canUpdate].getArrivalTime())/(float)result.getResult()[canUpdate].getEffort()));
                    result.getResult()[canUpdate].setFlag(1);
                    tot++;
                }
            }
            return CommonUtils.printJSONResultString(new JSONFullResult(result));

        }
    }



