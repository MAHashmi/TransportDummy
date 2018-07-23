package com.transport.verspaetungConnections.repository;

import com.transport.verspaetungConnections.model.StopTime;

import java.util.Iterator;
import java.util.List;

/**
 * Basic Interface for data operations relating to StopTime data.
 * An implementation would define the actual data operations.
 * @see StopTime
 */
public interface StopTimeRepository {
    public void add(StopTime stopTime);
    public void addAll(Iterator<StopTime> iterator);
    public List<StopTime> findAllStopTimeByStopId(int id);
}
