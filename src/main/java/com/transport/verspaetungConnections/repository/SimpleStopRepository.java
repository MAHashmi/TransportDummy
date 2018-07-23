package com.transport.verspaetungConnections.repository;

import com.transport.verspaetungConnections.model.Stop;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A simple implementation based on HashMap of the stop repository.
 * @see StopRepository
 * @see HashMap
 */
@Repository
public class SimpleStopRepository implements StopRepository {

    /**
     * Static storage for entities
     */
    private static HashMap<Integer, Stop> stops = new HashMap<>();

    /**
     * Method to add an instance of the entity to the repository
     * @param stop instance to be added.
     */
    @Override
    public void add(@NotNull Stop stop) {
        stops.put(stop.getId(), stop);
    }

    /**
     * Method to add multiple instances of the entity into the repository
     * @param iterator iterator containing instances to be added.
     */
    @Override
    public void addAll(Iterator<Stop> iterator) {
        while(iterator.hasNext()){
            this.add(iterator.next());
        }
    }

    /**
     * Method to find instance of stop entity correspoding to the provided id.
     * @param id id of the stop whose instance is desired
     * @return Instance of Stop corresponding to the given id, null if not found.
     */
    @Override
    public Stop findStopById(int id) {
        return stops.get(id);
    }

    /**
     * Method to find the first instance of stop entity correspoding to the provided location.
     * @param x x co-ordinate of the location
     * @param y y co-ordinate of the location
     * @return Instance of Stop corresponding to the given location, null if not found.
     */
    @Override
    public Stop findStopByXAndY(int x, int y) {
        for(Stop stop: stops.values()){
            if(stop.getX() == x && stop.getY() == y){
                return stop;
            }
        }
        return null;
    }
}
