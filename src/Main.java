import contollers.ApplicationController;
import entity.Query;
import services.ServiceForParsing;
import services.ServiceForStatistics;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        ApplicationController applicationController = new ApplicationController();
        applicationController.startApplication();
    }
}
