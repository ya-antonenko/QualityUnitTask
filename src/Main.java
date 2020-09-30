import entity.Query;
import services.ServiceForParsing;
import services.ServiceForStatistics;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        ServiceForParsing serviceForParsing = new ServiceForParsing();
        ServiceForStatistics serviceForStatistics = new ServiceForStatistics();
        List<String> inputList = serviceForParsing.fromConsoleInputToList();
        List<Query> queryList = serviceForParsing.fromAListOfStringsToAListOfQuery(inputList);
        System.out.println(queryList.toString());
        List<Query> finalList = serviceForStatistics.countingAverageTime(queryList);
        if (finalList.isEmpty()) System.out.println("bad request!");
        for (Query query : finalList){
            if (query.getAverageWaitingTime() == 0){
                System.out.println(query.toString() + "average time : -");
            }else {
                System.out.println(query.toString() + "average time : " + query.getAverageWaitingTime());
            }
        }
    }
}
