package commons;

import com.google.gson.annotations.JsonAdapter;
import objects.*;

public class CommonUtils {



    public static void printJSONResult(JSONFullResult result){
        System.out.print("|");
        System.out.print("PROCESS");
        System.out.print("\t|");
        System.out.print("ARRIVAL TIME");
        System.out.print("\t|");
        System.out.print("SERVICE TIME (TS)");
        System.out.print("\t|");
        System.out.print("WAITING TIME");
        System.out.print("\t|");
        System.out.print("FINISH TIME");
        System.out.print("\t|");
        System.out.print("TURN AROUND TIME (TR)");
        System.out.print("\t|");
        System.out.printf("TR/TS");
        System.out.print("\t|\n");
        for (JSONResult resultResult : result.getResults()) {
            System.out.print("|");
            System.out.print(resultResult.getProcess());
            System.out.print("\t\t\t|");
            System.out.print(resultResult.getArrivalTime());
            System.out.print("\t\t\t\t|");
            System.out.print(resultResult.getServiceTime());
            System.out.print("\t\t\t\t\t|");
            System.out.print(resultResult.getWaitingTime());
            System.out.print("\t\t\t\t|");
            System.out.print(resultResult.getFinishTime());
            System.out.print("\t\t\t\t|");
            System.out.print(resultResult.getTurnAroundTime());
            System.out.print("\t\t\t\t\t\t|");
            System.out.printf("%.2f", resultResult.getTrTs());
            System.out.print("\t|\n");
        }
        System.out.print("|");
        System.out.print("MEAN");
        System.out.print("\t\t|");
        System.out.print("\t\t\t\t|");
        System.out.print("\t\t\t\t\t|");
        System.out.print("\t\t\t\t|");
        System.out.print("\t\t\t\t|");
        System.out.printf("%.2f", result.getAverageTr());
        System.out.print("\t\t\t\t\t|");
        System.out.printf("%.2f", result.getAverageTrTs());
        System.out.print("\t|\n");
    }

    public static void informAlg(String alg){
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Mostrando resultados para el algoritmo " + alg);
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public static Result toResult(Work work) {
        Result result = new Result();
        result.setResult(new SingleResult[work.getScenarios().size()]);
        for(int i=0;i<result.getResult().length;i++){
            result.getResult()[i] = new SingleResult();
            result.getResult()[i].setProcess(work.getScenarios().get(i).getName());
            result.getResult()[i].setEffort(work.getScenarios().get(i).getEffort());
            result.getResult()[i].setK(work.getScenarios().get(i).getEffort());
            result.getResult()[i].setArrivalTime(work.getScenarios().get(i).getArrivalTime());
            result.getResult()[i].setArr(work.getScenarios().get(i).getArrivalTime());
            result.getResult()[i].setPriority(work.getScenarios().get(i).getPriority());
        }
        return result;
    }

    public static String printJSONResultString(JSONFullResult result){
        StringBuffer sb = new StringBuffer();
        sb.append("|");
        sb.append("PROCESO");
        sb.append("\t\t|");
        sb.append("TIEMPO DE ARRIBO");
        sb.append("\t|");
        sb.append("ESFUERZO");
        sb.append("\t|");
        sb.append("TIEMPO ESPERADO");
        sb.append("\t|");
        sb.append("TIEMPO DE FIN");
        sb.append("\t|");
        sb.append("TURN AROUND TIME (TR)");
        sb.append("\t|");
        sb.append("TR/TS");
        sb.append("\t|\n");
        for (JSONResult resultResult : result.getResults()) {
            sb.append("|");
            sb.append(resultResult.getProcess());
            sb.append("\t\t\t\t|");
            sb.append(resultResult.getArrivalTime());
            sb.append("\t\t\t\t\t|");
            sb.append(resultResult.getServiceTime());
            sb.append("\t\t\t|");
            sb.append(resultResult.getWaitingTime());
            sb.append("\t\t\t\t\t|");
            sb.append(resultResult.getFinishTime());
            sb.append("\t\t\t\t|");
            sb.append(resultResult.getTurnAroundTime());
            sb.append("\t\t\t\t\t\t|");
            sb.append(String.format("%.2f", resultResult.getTrTs()));
            sb.append("\t\t|\n");
        }
        //Totales
        sb.append("\n");
        sb.append("->Promedio TR: ");
        sb.append(String.format("%.2f", result.getAverageTr()));
        sb.append("\n->Promedio TR/TS: ");
        sb.append(String.format("%.2f", result.getAverageTrTs()));
        sb.append("\n->Esfuerzo Total: ");
        int totalEffort = 0;
        int maxFinish = 0;
        int minWait = 0 , maxWait = 0, totalWait = 0;
        for(JSONResult r : result.getResults()){
            if(r.getWaitingTime()>minWait)
                minWait=r.getWaitingTime();
        }
        for (JSONResult r : result.getResults()) {
            totalEffort += r.getServiceTime();
            if(r.getFinishTime()>maxFinish)
                maxFinish=r.getFinishTime();
            if(r.getWaitingTime()>maxWait)
                maxWait=r.getWaitingTime();
            if(r.getWaitingTime()<minWait)
                minWait=r.getWaitingTime();
            totalWait+=r.getWaitingTime();
        }
        sb.append(totalEffort);
        sb.append("\n->Tiempo Total necesitado: ");
        sb.append(maxFinish);
        sb.append("\n->Tiempo perdido en cambios de contexto: ");
        sb.append(result.getChangeContextCount());
        sb.append("\n->Tiempo minimo de espera: ");
        sb.append(minWait);
        sb.append("\n->Tiempo maximo de espera: ");
        sb.append(maxWait);
        sb.append("\n->Tiempo promedio de espera: ");
        sb.append(totalWait/result.getResults().size());
        sb.append("\n");
        return sb.toString();
    }

    public static String informAlgString(String alg){
        StringBuffer sb = new StringBuffer();
        sb.append("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
        sb.append("Mostrando resultados para el algoritmo " + alg + "\n");
        sb.append("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
        return sb.toString();
    }
}
