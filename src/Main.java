import entity.Query;
import services.ServiceForString;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ServiceForString serviceForString = new ServiceForString();
        List<String> inputList = serviceForString.fromConsoleInputToList();
        List<Query> queryList = serviceForString.fromAListOfStringsToAListOfQuery(inputList);
        System.out.println(queryList.toString());
    }
}
