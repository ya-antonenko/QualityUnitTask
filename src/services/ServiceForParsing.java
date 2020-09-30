package services;

import entity.Query;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceForParsing {
    private final String waitingTimeline = "C";
    private final String queryLine = "D";
    private final int sizeInputString = 0;
    private final int sizeQueryWithoutDateTo = 5;
    private final int standardSizeQueryAndTimeline = 6;

    public List<String> fromConsoleInputToList() {
        List<String> input = new ArrayList();
        Scanner scan = new Scanner(System.in);
        System.out.println("Input (to end press enter 2 times!): ");
        while (true) {
            String current = scan.nextLine();
            if (current.equals("")) break;
            input.add(current);
        }
        scan.close();
        return input;
    }

    public List<String> fromFileReadToList() {
        List<String> input = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Specify the absolute path to the file: ");
        String pathToTheFile = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToTheFile))) {
            String inputString;
            while ((inputString = br.readLine()) != null) {
                input.add(inputString);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Incorrect file path!");
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            scanner.close();
        }
        return input;
    }

    public List<Query> fromListOfStringsToListOfQuery(List<String> stringList) {
        List<Query> queryList = new ArrayList<>();
        try {
            queryList = new ArrayList<>(Integer.valueOf(stringList.get(sizeInputString)));
            stringList.remove(sizeInputString);
        } catch (NumberFormatException exception) {
            System.out.println("The first line does not indicate the number of requests. " +
                    "This will increase the execution time of the application!");
        }
        String separator = "\\s";
        for (int i = 0; i < stringList.size(); i++) {
            String changeString = stringList.get(i).replace('-', ' ');
            String[] arrayParameters = changeString.split(separator);
            try {
                Query query = createQueryEntityFromArray(arrayParameters);
                if (query.getDateFrom() != null) queryList.add(query);
            } catch (ParseException e) {
                System.out.println("Request not successful!" + "\n" + displayArray(arrayParameters));
                continue;
            }
        }
        return queryList;
    }

    private Query createQueryEntityFromArray(String[] arrayParameters) throws ParseException {
        if (arrayParameters.length == standardSizeQueryAndTimeline &&
                arrayParameters[0].equals(waitingTimeline)) {
            Query query = new Query(arrayParameters[0], arrayParameters[1], arrayParameters[2],
                    arrayParameters[3], arrayParameters[4], Integer.valueOf(arrayParameters[5]));
            return query;
        } else if (arrayParameters.length == standardSizeQueryAndTimeline &&
                arrayParameters[0].equals(queryLine)) {
            Query query = new Query(arrayParameters[0], arrayParameters[1], arrayParameters[2],
                    arrayParameters[3], arrayParameters[4], arrayParameters[5]);
            return query;
        } else if (arrayParameters.length == sizeQueryWithoutDateTo) {
            Query query = new Query(arrayParameters[0], arrayParameters[1], arrayParameters[2],
                    arrayParameters[3], arrayParameters[4]);
            return query;
        }
        return new Query();
    }

    private String displayArray(String[] arrayString) {
        String result = "";
        for (int i = 0; i < arrayString.length; i++) {
            result += arrayString[i].concat(" ");
        }
        return result;
    }
}
