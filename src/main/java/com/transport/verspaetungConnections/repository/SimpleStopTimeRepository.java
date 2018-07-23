package com.transport.verspaetungConnections.repository;

import com.transport.verspaetungConnections.model.StopTime;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A simple implementation based on HashMap of the stop time repository.
 * @see StopTimeRepository
 * @see HashMap
 */
@Repository
public class SimpleStopTimeRepository implements StopTimeRepository{

    /**
     * Static storage for entities
     */
    private static HashMap<Integer, List<StopTime>> stopTimes = new HashMap<>();

    /**
     * Method to add an instance of the entity to the repository
     * @param stopTime instance to be added.
     */
    @Override
    public void add(@NotNull StopTime stopTime) {
        List<StopTime> mStopTimes = stopTimes.get(stopTime.getStopId());

        if(mStopTimes==null){
            mStopTimes = new ArrayList<>();
        }

        mStopTimes.add(stopTime);

        stopTimes.put(stopTime.getStopId(), mStopTimes);
    }

    /**
     * Method to add multiple instances of the entity into the repository
     * @param iterator iterator containing instances to be added.
     */
    @Override
    public void addAll(Iterator<StopTime> iterator) {
        while(iterator.hasNext()){
            this.add(iterator.next());
        }
    }

    /**
     * Method to find instance of stop time entity correspoding to the provided id.
     * @param id id of the stop time whose instance is desired
     * @return Instance of StopTime corresponding to the given id, null if not found.
     */
    @Override
    public List<StopTime> findAllStopTimeByStopId(int id) {
        return stopTimes.get(id);
    }
}
