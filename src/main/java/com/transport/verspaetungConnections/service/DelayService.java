package com.transport.verspaetungConnections.service;

import com.transport.verspaetungConnections.model.Delay;
import com.transport.verspaetungConnections.repository.DelayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Standard service that provides information about possible delays.
 */
@Service
public class DelayService {

    /**
     * Dependency for available delay repository
     * @see DelayRepository
     */
    @Autowired
    private DelayRepository delayRepository;

    /**
     * Method that checks whether the line with given name is delayed or not
     * @param lineName Name of the line whose delay info is desired
     * @return true; if it is delayes, false; if not
     */
    public boolean isLineDelayed(String lineName){
        Delay delay = delayRepository.findDelayByLineName(lineName);

        // line is late only if the delay in mins is greater than zero
        if(delay != null && delay.getDelayMins() > 0){
            return true;
        }

        return false;
    }
}
