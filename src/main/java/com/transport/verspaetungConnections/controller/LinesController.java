package com.transport.verspaetungConnections.controller;

import com.transport.verspaetungConnections.model.Line;
import com.transport.verspaetungConnections.repository.LineRepository;
import com.transport.verspaetungConnections.service.DelayService;
import com.transport.verspaetungConnections.service.LineAtStopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Rest Controller, providing information about transport lines
 */

@RestController
@RequestMapping("/")
public class LinesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinesController.class);

    /**
     * Dependency for delay information
     * @see DelayService
     */
    @Autowired
    private DelayService delayService;

    /**
     * Dependency for available lines at a stop
     * @see LineAtStopService
     */
    @Autowired
    private LineAtStopService lineAtStopService;

    /**
     * Endpoint for checking whether a line is delayed or not
     * Returns a boolean within the response body which denotes delay in given line
     * @param lineName the line name whose delay info is desired
     * @return true, if the line is delayed. false otherwise.
     */
    @RequestMapping(value = "/lines/{lineName}", method = RequestMethod.GET)
    @ResponseBody
    public boolean isLineDelayed(@PathVariable("lineName") String lineName){
        return delayService.isLineDelayed(lineName);
    }

    /**
     * Endpoint for checking current lines at a particular position
     *
     * @param x x co-ordinates of the location
     * @param y y co-ordinates of the location
     * @param timestamp current time in format HH:MM:SS
     * @return A list of available lines for given parameters in response body
     */
    @RequestMapping(value = "/lines", method = RequestMethod.GET)
    public ResponseEntity<List<Line>> getCurrentLinesForPosition(@RequestParam int x,
                                                                 @RequestParam int y,
                                                                 @RequestParam String timestamp){
        List<Line> result = new ArrayList<>();
        try{
            result = lineAtStopService.getCurrentLinesForPosition(x, y, timestamp);
        }catch(ParseException e){
            LOGGER.error("Cannot parse time stamp according to the format HH:mm:ss");
            e.printStackTrace();

            return new ResponseEntity<List<Line>>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<List<Line>>(result, HttpStatus.OK);
    }
}
