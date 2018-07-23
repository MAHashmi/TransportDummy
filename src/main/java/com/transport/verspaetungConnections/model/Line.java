package com.transport.verspaetungConnections.model;

import com.opencsv.bean.CsvBindByName;

/**
 * Basic Data Model for Line informations
 * @see CsvBindByName annotation to map columns in CSV file
 */
public class Line {

    @CsvBindByName(column = "line_id", required = true)
    private int id;

    @CsvBindByName(column = "line_name")
    private String name;

    public Line() {
    }

    public Line(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
