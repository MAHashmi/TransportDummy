package verspaetungConnections.repository;

import com.transport.verspaetungConnections.model.Delay;
import com.transport.verspaetungConnections.model.Line;
import com.transport.verspaetungConnections.model.Stop;
import com.transport.verspaetungConnections.model.StopTime;
import com.transport.verspaetungConnections.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(value = SpringRunner.class)
public class RepositoryTest {

    @TestConfiguration
    static class RepositoryTestContextConfiguration{
        @Bean
        public DelayRepository delayRepository(){
            return new SimpleDelayRepostory();
        }

        @Bean
        public LineRepository lineRepository(){
            return new SimpleLineRepository();
        }

        @Bean
        public StopRepository stopRepository(){
            return new SimpleStopRepository();
        }

        @Bean
        public StopTimeRepository stopTimeRepository(){
            return new SimpleStopTimeRepository();
        }
    }

    @Autowired
    private DelayRepository delayRepository;

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private StopTimeRepository stopTimeRepository;

    @Test
    public void testDelayRepository(){
        Delay delay = new Delay("S1", 2);

        delayRepository.add(delay);

        Delay fromRep = delayRepository.findDelayByLineName(delay.getLineName());

        assertEquals(fromRep, delay);
    }

    @Test
    public void testLineRepository(){
        Line line = new Line(1, "U2");

        lineRepository.add(line);

        Line fromRep = lineRepository.findLineById(line.getId());

        assertEquals(fromRep, line);
    }

    @Test
    public void testStopRepository(){
        Stop stop = new Stop(1, 5, 7);

        stopRepository.add(stop);

        Stop fromRep1 = stopRepository.findStopById(stop.getId());
        assertEquals(fromRep1, stop);

        Stop fromRep2 = stopRepository.findStopByXAndY(stop.getX(), stop.getY());
        assertEquals(fromRep2, stop);

        Stop fromRep3 = stopRepository.findStopByXAndY(0, 0);
        assertEquals(fromRep3, null);
    }

    @Test
    public void testStopTimeRepository(){
        List<StopTime> stopTimes = new ArrayList<>();
        stopTimes.add(new StopTime(1, 2, "10:28:00"));
        stopTimes.add(new StopTime(4, 3, "10:25:00"));
        stopTimes.add(new StopTime(6, 2, "10:30:00"));


        stopTimeRepository.addAll(stopTimes.iterator());

        List<StopTime> fromRep = stopTimeRepository.findAllStopTimeByStopId(2);

        assertTrue(fromRep.contains(stopTimes.get(0)));
        assertTrue(fromRep.contains(stopTimes.get(2)));
        assertFalse(fromRep.contains(stopTimes.get(1)));
    }
}
