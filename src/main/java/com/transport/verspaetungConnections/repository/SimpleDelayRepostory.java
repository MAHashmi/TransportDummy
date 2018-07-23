package com.transport.verspaetungConnections.repository;

import com.transport.verspaetungConnections.model.Delay;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A simple implementation based on HashMap of the delay repository.
 * @see DelayRepository
 * @see HashMap
 */
@Repository
public class SimpleDelayRepostory implements DelayRepository {

    /**
     * Static storage for delay entities
     */
    private static HashMap<String, Delay> delays = new HashMap<>();

    /**
     * Method to add an instance of the entity to the repository
     * @param delay instance to be added.
     */
    @Override
    public void add(@NotNull Delay delay) {
        delays.put(delay.getLineName(), delay);
    }

    /**
     * Method to add multiple instances of the entity into the repository
     * @param iterator iterator containing instances to be added.
     */
    @Override
    public void addAll(Iterator<Delay> iterator) {
        while(iterator.hasNext()){
            this.add(iterator.next());
        }
    }

    /**
     * Method to find instance of delay entity correspoding to the line name.
     * @param lineName name of the line whose delay is desired
     * @return Instance of Delay corresponding to the given line name, null if not found.
     */
    @Override
    public Delay findDelayByLineName(String lineName) {
        return delays.get(lineName);
    }
}
