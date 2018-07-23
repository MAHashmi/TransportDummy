package verspaetungConnections.service;

import com.transport.verspaetungConnections.model.Line;
import com.transport.verspaetungConnections.model.Stop;
import com.transport.verspaetungConnections.model.StopTime;
import com.transport.verspaetungConnections.repository.DelayRepository;
import com.transport.verspaetungConnections.repository.LineRepository;
import com.transport.verspaetungConnections.repository.StopRepository;
import com.transport.verspaetungConnections.repository.StopTimeRepository;
import com.transport.verspaetungConnections.service.DelayService;
import com.transport.verspaetungConnections.service.LineAtStopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(value = SpringRunner.class)
public class LineServiceTest {

    @TestConfiguration
    static class LineServiceTestContextConfiguration{
        @Bean
        public LineAtStopService delayService(){
            return new LineAtStopService();
        }
    }

    @Autowired
    private LineAtStopService lineAtStopService;

    @MockBean
    private LineRepository lineRepository;

    @MockBean
    private StopRepository stopRepository;

    @MockBean
    private StopTimeRepository stopTimeRepository;

    @Test
    public void testGetCurrentLinesForPosition(){
        Stop stop = new Stop(1, 2, 2);

        List<StopTime> stopTimes = new ArrayList<>();
        stopTimes.add(new StopTime(1, stop.getId(), "10:00:00"));
        stopTimes.add(new StopTime(1, stop.getId(), "10:05:00"));
        stopTimes.add(new StopTime(2, stop.getId(), "10:10:00"));
        stopTimes.add(new StopTime(3, stop.getId(), "09:00:00"));

        Line line1 = new Line(1, "S1");
        Line line2 = new Line(2, "S2");
        Line line3 = new Line(3, "S3");

        Mockito.when(stopRepository.findStopByXAndY(2,2)).thenReturn(stop);

        Mockito.when(stopTimeRepository.findAllStopTimeByStopId(stop.getId())).thenReturn(stopTimes);

        Mockito.when(lineRepository.findLineById(1)).thenReturn(line1);
        Mockito.when(lineRepository.findLineById(2)).thenReturn(line2);
        Mockito.when(lineRepository.findLineById(3)).thenReturn(line3);


        try{
            List<Line> result = lineAtStopService.getCurrentLinesForPosition(2,2,"10:00:00");
            assertTrue(result.contains(line1));
            assertTrue(result.contains(line2));
            assertFalse(result.contains(line3));
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

}
