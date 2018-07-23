package com.transport.verspaetungConnections.repository;

import com.transport.verspaetungConnections.model.Line;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A simple implementation based on HashMap of the line repository.
 * @see LineRepository
 * @see HashMap
 */
@Repository
public class SimpleLineRepository implements LineRepository {

    /**
     * Static storage for entities
     */
    private static HashMap<Integer, Line> lines = new HashMap<>();

    /**
     * Method to add an instance of the entity to the repository
     * @param line instance to be added.
     */
    @Override
    public void add(@NotNull Line line) {
        lines.put(line.getId(), line);
    }

    /**
     * Method to add multiple instances of the entity into the repository
     * @param iterator iterator containing instances to be added.
     */
    @Override
    public void addAll(Iterator<Line> iterator) {
        while(iterator.hasNext()){
            this.add(iterator.next());
        }
    }

    /**
     * Method to find instance of line entity correspoding to the provided line id.
     * @param id id of the line whose instance is desired
     * @return Instance of Line corresponding to the given line id, null if not found.
     */
    @Override
    public Line findLineById(int id) {
        return lines.get(id);
    }
}
