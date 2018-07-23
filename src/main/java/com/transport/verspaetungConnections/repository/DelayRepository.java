package com.transport.verspaetungConnections.repository;

import com.transport.verspaetungConnections.model.Delay;

import java.util.Iterator;

/**
 * Basic Interface for data operations relating to Delay data.
 * An implementation would define the actual data operations.
 * @see Delay
 */
public interface DelayRepository {
    public void add(Delay delay);
    public void addAll(Iterator<Delay> iterator);
    public Delay findDelayByLineName(String lineName);
}
