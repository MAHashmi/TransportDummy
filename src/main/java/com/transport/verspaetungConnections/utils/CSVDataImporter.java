package com.transport.verspaetungConnections.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.transport.verspaetungConnections.model.Delay;
import com.transport.verspaetungConnections.model.Line;
import com.transport.verspaetungConnections.model.Stop;
import com.transport.verspaetungConnections.model.StopTime;
import com.transport.verspaetungConnections.repository.DelayRepository;
import com.transport.verspaetungConnections.repository.LineRepository;
import com.transport.verspaetungConnections.repository.StopRepository;
import com.transport.verspaetungConnections.repository.StopTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * CSV extension of AbstractDataImporter as a Component.
 * Imports data from the csv data files located in /resources.
 * @see AbstractDataImporter
 * @see Component
 */
@Component
public class CSVDataImporter extends AbstractDataImporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVDataImporter.class);

    /**
     * Location of delays.csv file within resources folder.
     * Property to be found in application.properties.
     */
    @Value("${app.raw.data.delays}")
    private String delaysFileName;

    /**
     * Location of lines.csv file within resources folder.
     * Property to be found in application.properties
     */
    @Value("${app.raw.data.lines}")
    private String linesFileName;

    /**
     * Location of stop_times.csv file within resources folder.
     * Property to be found in application.properties
     */
    @Value("${app.raw.data.stoptimes}")
    private String stopTimesFileName;

    /**
     * Location of stops.csv file within resources folder.
     * Property to be found in application.properties
     */
    @Value("${app.raw.data.stops}")
    private String stopsFileName;

    /**
     * Dependency for available delay repository
     * @see DelayRepository
     */
    @Autowired
    private DelayRepository delayRepository;

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
     * Overrides the importAllData() from super to import all data.
     * PostConstruct annotation calls this method after construction.
     * @see PostConstruct
     */
    @PostConstruct
    @Override
    public void importAllData() {
        super.importAllData();
    }

    /**
     * Specific implementation to import data from csv file.
     */
    @Override
    protected void importDelaysData() {
        // Only if file exisits and available
        if(getClass().getResource(delaysFileName)!=null){
            File file = new File(getClass().getResource(delaysFileName).getFile());

            try(Reader reader = Files.newBufferedReader(file.toPath())){
                // Using openCSV to read all entries as Delay objects
                CsvToBean<Delay> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Delay.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // Add to Repository
                delayRepository.addAll(csvToBean.iterator());
            }catch (IOException e){
                LOGGER.error("Error importing data from: {}", delaysFileName);
                e.printStackTrace();
            }
        }else{
            LOGGER.error("No such file as: {}", delaysFileName);
        }
    }

    /**
     * Specific implementation to import data from csv file.
     */
    @Override
    protected void importLinesData() {
        // Only if file exisits and available
        if(getClass().getResource(linesFileName)!=null){
            File file = new File(getClass().getResource(linesFileName).getFile());

            try(Reader reader = Files.newBufferedReader(file.toPath())){
                // Using openCSV to read all entries as Line objects
                CsvToBean<Line> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Line.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // Add to Repository
                lineRepository.addAll(csvToBean.iterator());
            }catch (IOException e){
                LOGGER.error("Error importing data from: {}", linesFileName);
                e.printStackTrace();
            }
        }else{
            LOGGER.error("No such file as: {}", linesFileName);
        }
    }

    /**
     * Specific implementation to import data from csv file.
     */
    @Override
    protected void importStopsData() {
        // Only if file exisits and available
        if(getClass().getResource(stopsFileName)!=null){
            File file = new File(getClass().getResource(stopsFileName).getFile());

            try(Reader reader = Files.newBufferedReader(file.toPath())){
                // Using openCSV to read all entries as Stop objects
                CsvToBean<Stop> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Stop.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // Add to Repository
                stopRepository.addAll(csvToBean.iterator());
            }catch (IOException e){
                LOGGER.error("Error importing data from: {}", stopsFileName);
                e.printStackTrace();
            }
        }else{
            LOGGER.error("No such file as: {}", stopsFileName);
        }
    }

    /**
     * Specific implementation to import data from csv file.
     */
    @Override
    protected void importStopTimesData() {
        // Only if file exisits and available
        if(getClass().getResource(stopTimesFileName)!=null){
            File file = new File(getClass().getResource(stopTimesFileName).getFile());

            try(Reader reader = Files.newBufferedReader(file.toPath())){
                // Using openCSV to read all entries as StopTime objects
                CsvToBean<StopTime> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(StopTime.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // Add to Repository
                stopTimeRepository.addAll(csvToBean.iterator());
            }catch (IOException e){
                LOGGER.error("Error importing data from: {}", stopTimesFileName);
                e.printStackTrace();
            }
        }else{
            LOGGER.error("No such file as: {}", stopTimesFileName);
        }
    }
}
