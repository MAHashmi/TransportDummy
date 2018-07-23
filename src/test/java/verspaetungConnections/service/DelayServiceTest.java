package verspaetungConnections.service;

import com.transport.verspaetungConnections.model.Delay;
import com.transport.verspaetungConnections.repository.DelayRepository;
import com.transport.verspaetungConnections.service.DelayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(value = SpringRunner.class)
public class DelayServiceTest {

    @TestConfiguration
    static class DelayServiceTestContextConfiguration{
        @Bean
        public DelayService delayService(){
            return new DelayService();
        }
    }

    @Autowired
    private DelayService delayService;

    @MockBean
    private DelayRepository delayRepository;

    @Test
    public void testIsLineDelayed() {
        Delay delay = new Delay("S1", 3);

        Mockito.when(delayRepository.findDelayByLineName(delay.getLineName())).thenReturn(delay);

        assertTrue(delayService.isLineDelayed("S1"));
        assertFalse(delayService.isLineDelayed("S2"));
    }
}
