package contollers;

import entity.Query;
import services.ServiceForParsing;
import services.ServiceForStatistics;

import java.util.List;
import java.util.Scanner;

public class ApplicationController {
    ServiceForParsing serviceForParsing = new ServiceForParsing();
    ServiceForStatistics serviceForStatistics = new ServiceForStatistics();
    private String fromConsole = "1";
    private String fromFile = "2";
    private String toQuit = "q";

    public void startApplication() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!" + "\n" + "Please, make a choice:" + "\n" +
                "1 - enter a string in console;" + "\n" + "2 - download from file;" + "\n" +
                "Q - close application.");
        String firstStep = scanner.nextLine();
        while (true) {
            if (firstStep.equals(fromConsole)) {
                List<Query> listWithAverageCustomerExpectation = createFinalListViaConsole();
                showResult(listWithAverageCustomerExpectation);
                break;
            } else if (firstStep.equals(fromFile)) {
                List<Query> listWithAverageCustomerExpectation = createFinalListViaFile();
                showResult(listWithAverageCustomerExpectation);
                break;
            } else if (firstStep.equalsIgnoreCase(toQuit)) {
                scanner.close();
                break;
            } else {
                System.out.println("Incorrect entry, please try again!");
                startApplication();
            }
        }
    }

    private List<Query> createFinalListViaConsole() {
        List<String> stringList = serviceForParsing.fromConsoleInputToList();
        List<Query> queryList = serviceForParsing.fromListOfStringsToListOfQuery(stringList);
        List<Query> listWithAverageCustomerExpectation = serviceForStatistics.countingAverageTime(queryList);
        return listWithAverageCustomerExpectation;
    }

    private List<Query> createFinalListViaFile() {
        List<String> stringList = serviceForParsing.fromFileReadToList();
        List<Query> queryList = serviceForParsing.fromListOfStringsToListOfQuery(stringList);
        List<Query> listWithAverageCustomerExpectation = serviceForStatistics.countingAverageTime(queryList);
        return listWithAverageCustomerExpectation;
    }

    private void showResult(List<Query> listWithAverageCustomerExpectation) {
        for (Query query : listWithAverageCustomerExpectation) {
            if (query.getAverageWaitingTime() == 0) {
                System.out.println(query.toString() + "average time : -");
            } else {
                System.out.println(query.toString() + "average time : " + query.getAverageWaitingTime());
            }
        }
    }
}
