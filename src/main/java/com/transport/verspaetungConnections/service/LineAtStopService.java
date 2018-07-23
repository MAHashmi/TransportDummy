package com.transport.verspaetungConnections.service;

import com.transport.verspaetungConnections.model.Line;
import com.transport.verspaetungConnections.model.Stop;
import com.transport.verspaetungConnections.model.StopTime;
import com.transport.verspaetungConnections.repository.LineRepository;
import com.transport.verspaetungConnections.repository.StopRepository;
import com.transport.verspaetungConnections.repository.StopTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Standard service that provides information about current lines at a particular location
 */
@Service
public class LineAtStopService {

    /**
     * Dependency for available line repository
     * @see LineRepository
     */
    @Autowired
    private LineRepository lineRepository;

    /**
     * Dependency for available stop repository
     * @see StopRepository
     */
    @Autowired
    private StopRepository stopRepository;

    /**
     * Dependency for available stop time repository
     * @see StopTimeRepository
     */
    @Autowired
    private StopTimeRepository stopTimeRepository;

    /**
     * Method that returns a list of current available lines at the given location.
     * @param x x co-ordinate of the location
     * @param y y co-ordinate of the location
     * @param timeStamp current time stamp in the format HH:MM:SS
     * @return List of Line objects containing id and names of the available lines
     * @throws ParseException
     * @see Line
     */
    public List<Line> getCurrentLinesForPosition(int x, int y, String timeStamp) throws ParseException {
        // find actual stop for available position
        Stop actualStop = stopRepository.findStopByXAndY(x, y);

        // if there is no actual stop at the available position, there are no lines
        if(actualStop==null){
            return new ArrayList<>();
        }

        // get all possible line connections for the particular stop
        List<StopTime> availableConnections = stopTimeRepository.findAllStopTimeByStopId(actualStop.getId());

        // list of available lines
        List<Line> currLines = new ArrayList<>();

        // check for available connections if they are available in present or future,
        // remove the ones from past
        for(StopTime st : availableConnections){
            if(st.compareTimeStamp(timeStamp)>=0){
                Line availableLine = lineRepository.findLineById(st.getLineId());
                // add only if its not already included
                if(!currLines.contains(availableLine)) {
                    currLines.add(lineRepository.findLineById(st.getLineId()));
                }
            }
        }

        return currLines;
    }
}
