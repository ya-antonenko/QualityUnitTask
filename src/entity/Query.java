package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Query {
    private String character;
    private String serviceID;
    private String questionTypeID;
    private String responseType;
    private Date dateFrom;
    private Date dateTo;
    private int time;

    private int averageWaitingTime = 0;
    private int quantityTimeline = 0;
    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public Query(String character, String serviceID, String questionTypeID, String responseType, String dateFrom, String dateTo)
    throws ParseException{
        this.character = character;
        this.serviceID = serviceID;
        this.questionTypeID = questionTypeID;
        this.responseType = responseType;
        this.dateFrom = format.parse(dateFrom);
        this.dateTo = format.parse(dateTo);
    }

    public Query(String character, String serviceID, String questionTypeID, String responseType, String dateFrom, int time)
    throws ParseException{
        this.character = character;
        this.serviceID = serviceID;
        this.questionTypeID = questionTypeID;
        this.responseType = responseType;
        this.dateFrom = format.parse(dateFrom);
        this.time = time;
    }

    public Query(String character, String serviceID, String questionTypeID, String responseType, String dateFrom)
            throws ParseException{
        //SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY");
        this.character = character;
        this.serviceID = serviceID;
        this.questionTypeID = questionTypeID;
        this.responseType = responseType;
        this.dateFrom = format.parse(dateFrom);
    }

    public Query() {
        super();
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getQuestionTypeID() {
        return questionTypeID;
    }

    public void setQuestionTypeID(String questionTypeID) {
        this.questionTypeID = questionTypeID;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) throws ParseException {
        this.dateFrom = format.parse(dateFrom);
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) throws ParseException{
        this.dateTo = format.parse(dateTo);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void setAverageWaitingTime(int averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public int getQuantityTimeline() {
        return quantityTimeline;
    }

    public void setQuantityTimeline(int quantityTimeline) {
        this.quantityTimeline = quantityTimeline;
    }

    @Override
    public String toString() {
        if (dateTo != null) {
            return "Query{" +
                    "character='" + character + '\'' +
                    ", serviceID='" + serviceID + '\'' +
                    ", questionTypeID='" + questionTypeID + '\'' +
                    ", responseType='" + responseType + '\'' +
                    ", dateFrom=" + format.format(dateFrom) +
                    ", dateTo=" + format.format(dateTo) +
                    '}';
        }else {
            return "Query{" +
                    "character='" + character + '\'' +
                    ", serviceID='" + serviceID + '\'' +
                    ", questionTypeID='" + questionTypeID + '\'' +
                    ", responseType='" + responseType + '\'' +
                    ", dateFrom=" + format.format(dateFrom) +
                    '}';
        }
    }
}
