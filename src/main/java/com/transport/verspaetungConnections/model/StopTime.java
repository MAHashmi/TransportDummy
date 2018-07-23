package com.transport.verspaetungConnections.model;

import com.opencsv.bean.CsvBindByName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Basic Data Model for Stop Time informations
 * @see CsvBindByName annotation to map columns in CSV file
 */
public class StopTime {

    @CsvBindByName(column = "line_id")
    private int lineId;

    @CsvBindByName(column = "stop_id", required = true)
    private int stopId;

    @CsvBindByName(column = "time", required = true)
    private String timeStamp;

    public StopTime() {
    }

    public StopTime(int lineId, int stopId, String timeStamp) {
        this.lineId = lineId;
        this.stopId = stopId;
        this.timeStamp = timeStamp;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Method to compare time stamp with given time stamp in format HH:MM:SS
     *
     * @param toBeCompared time stamp to be compared in format HH:MM:SS
     * @return integer denoting the comparison results.
     * @throws ParseException
     */
    public int compareTimeStamp(String toBeCompared) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

        Date d1 = df.parse(timeStamp);
        Date d2 = df.parse(toBeCompared);

        return d1.compareTo(d2);
    }
}
