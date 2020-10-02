package services;

import entity.Query;

import java.util.ArrayList;
import java.util.List;

public class ServiceForStatistics {

    private final String waitingTimeline = "C";
    private final String queryLine = "D";
    private final String allTypes = "*";

    public List<Query> countingAverageTime(List<Query> allQueryList) {
        List<Query> queryList = sortTheQueryList(allQueryList);
        List<Query> timelineList = sortTheTimelineList(allQueryList);
        if (queryList.isEmpty()) return new ArrayList<>();
        List<Query> listWithAverageCustomerExpectation = new ArrayList<>(queryList.size());
        for (int i = 0; i < queryList.size(); i++) {
            Query query = queryList.get(i);
            for (int j = 0; j < timelineList.size(); j++) {
                if (comparisionOfParameters(query, timelineList.get(j))) {
                    int currentAverageWaiting = query.getAverageWaitingTime();
                    currentAverageWaiting += timelineList.get(j).getTime();
                    query.setAverageWaitingTime(currentAverageWaiting);
                    int quantityTimeline = query.getQuantityTimeline() + 1;
                    query.setQuantityTimeline(quantityTimeline);
                }
            }
            try{
                int finalAverageWaitingTime;
                finalAverageWaitingTime = query.getAverageWaitingTime() / query.getQuantityTimeline();
                query.setAverageWaitingTime(finalAverageWaitingTime);
                listWithAverageCustomerExpectation.add(query);
            }catch (ArithmeticException e){
                listWithAverageCustomerExpectation.add(query);
                continue;
            }

        }
        return listWithAverageCustomerExpectation;
    }

    private List<Query> sortTheQueryList(List<Query> allQueryList) {
        List<Query> queryList = new ArrayList<>();
        for (int i = 0; i < allQueryList.size(); i++) {
            Query query = allQueryList.get(i);
            if (query.getCharacter().equals(queryLine) && !queryList.contains(query))
                queryList.add(query);
        }
        return queryList;
    }

    private List<Query> sortTheTimelineList(List<Query> allQueryList) {
        List<Query> timelineList = new ArrayList<>();
        for (int i = 0; i < allQueryList.size(); i++) {
            Query query = allQueryList.get(i);
            if (query.getCharacter().equals(waitingTimeline))
                timelineList.add(query);
        }
        return timelineList;
    }

    private boolean comparisionOfParameters(Query query, Query timeline) {
        if ((query.getServiceID().toCharArray()[0] == timeline.getServiceID().toCharArray()[0] ||
                query.getServiceID().equals(allTypes)) &&
                (query.getQuestionTypeID().toCharArray()[0] == timeline.getQuestionTypeID().toCharArray()[0] ||
                        query.getQuestionTypeID().equals(allTypes))) {
            if (query.getDateTo() != null) {
                if ((query.getDateFrom().before(timeline.getDateFrom()) ||
                        query.getDateFrom().equals(timeline.getDateFrom())) &&
                        (query.getDateTo().after(timeline.getDateFrom()) ||
                                query.getDateTo().equals(timeline.getDateFrom()))) return true;
            }
            if (query.getDateTo() == null) {
                if ((query.getDateFrom().equals(timeline.getDateFrom()))) return true;
            }
        }
        return false;
    }
}
