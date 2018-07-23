package com.transport.verspaetungConnections.model;

import com.opencsv.bean.CsvBindByName;

/**
 * Basic Data Model for Stop informations
 * @see CsvBindByName annotation to map columns in CSV file
 */
public class Stop {

    @CsvBindByName(column = "stop_id", required = true)
    private int id;

    @CsvBindByName(column = "x")
    private int x;

    @CsvBindByName(column = "y")
    private int y;

    public Stop(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Stop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
