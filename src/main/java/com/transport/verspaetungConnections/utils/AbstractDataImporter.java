package com.transport.verspaetungConnections.utils;

/**
 * Abstract implementation to import data for different types of data.
 * An extension itself would define the particularities of the way data is imported e.g. CSV, JSON etc.
 * At least one implementation needs to be initialized for the data to be actually imported.
 */
public abstract class AbstractDataImporter {

    public void importAllData(){
        this.importDelaysData();
        this.importLinesData();
        this.importStopsData();
        this.importStopTimesData();
    }


    protected abstract void importDelaysData();
    protected abstract void importLinesData();
    protected abstract void importStopsData();
    protected abstract void importStopTimesData();
}
