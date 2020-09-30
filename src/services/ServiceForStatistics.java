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
                    int quantityTimeline = query.getQuantityTimeline() + 1;
                    int resultAverageWaiting = (currentAverageWaiting +
                            timelineList.get(j).getTime()) / quantityTimeline;
                    query.setAverageWaitingTime(resultAverageWaiting);
                    query.setQuantityTimeline(quantityTimeline);
                    if (!listWithAverageCustomerExpectation.contains(query))
                        listWithAverageCustomerExpectation.add(query);
                    if (listWithAverageCustomerExpectation.contains(query)) {
                        queryList.set(listWithAverageCustomerExpectation.indexOf(query), query);
                    }
                }
            }
            if (query.getAverageWaitingTime() == 0) listWithAverageCustomerExpectation.add(query);
        }
        return listWithAverageCustomerExpectation;
    }

    private List<Query> sortTheQueryList(List<Query> allQueryList) {
        List<Query> queryList = new ArrayList<>();
        for (int i = 0; i < allQueryList.size(); i++) {
            if (allQueryList.get(i).getCharacter().equals(queryLine)) queryList.add(allQueryList.get(i));
        }
        return queryList;
    }

    private List<Query> sortTheTimelineList(List<Query> allQueryList) {
        List<Query> timelineList = new ArrayList<>();
        for (int i = 0; i < allQueryList.size(); i++) {
            if (allQueryList.get(i).getCharacter().equals(waitingTimeline)) timelineList.add(allQueryList.get(i));
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
