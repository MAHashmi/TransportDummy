package com.transport.verspaetungConnections.repository;

import com.transport.verspaetungConnections.model.Delay;
import com.transport.verspaetungConnections.model.Line;

import java.util.Iterator;

/**
 * Basic Interface for data operations relating to Line data.
 * An implementation would define the actual data operations.
 * @see Line
 */
public interface LineRepository {
    public void add(Line line);
    public void addAll(Iterator<Line> iterator);
    public Line findLineById(int id);
}
