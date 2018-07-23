package com.transport.verspaetungConnections.model;

import com.opencsv.bean.CsvBindByName;

/**
 * Basic Data Model for Delay informations
 * @see CsvBindByName annotation to map columns in CSV file
 */
public class Delay {

    @CsvBindByName(column = "line_name", required = true)
    private String lineName;

    @CsvBindByName(column = "delay")
    private int delayMins;

    public Delay() {
    }

    public Delay(String lineName, int delayMins) {
        this.lineName = lineName;
        this.delayMins = delayMins;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getDelayMins() {
        return delayMins;
    }

    public void setDelayMins(int delayMins) {
        this.delayMins = delayMins;
    }
}
