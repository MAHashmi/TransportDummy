package com.transport.verspaetungConnections.repository;

import com.transport.verspaetungConnections.model.Stop;

import java.util.Iterator;

/**
 * Basic Interface for data operations relating to Stop data.
 * An implementation would define the actual data operations.
 * @see Stop
 */
public interface StopRepository {
    public void add(Stop stop);
    public void addAll(Iterator<Stop> iterator);
    public Stop findStopById(int id);
    public Stop findStopByXAndY(int x, int y);
}
