package verspaetungConnections.controller;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transport.verspaetungConnections.MyApplication;
import com.transport.verspaetungConnections.controller.LinesController;
import com.transport.verspaetungConnections.model.Line;
import com.transport.verspaetungConnections.service.DelayService;
import com.transport.verspaetungConnections.service.LineAtStopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LinesController.class, secure = false)
@ContextConfiguration(classes = MyApplication.class)
public class LinesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LineAtStopService lineAtStopService;

    @MockBean
    DelayService delayService;

    @Test
    public void testIsLineDelayed(){
        Mockito.when(delayService.isLineDelayed("S1")).thenReturn(true);
        Mockito.when(delayService.isLineDelayed("S2")).thenReturn(false);

        testIsLineDelayed("/lines/S1", "true");
        testIsLineDelayed("/lines/S2", "false");
    }

    private void testIsLineDelayed(String url, String expected){
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);

        try {
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            MockHttpServletResponse response = result.getResponse();

            assertEquals(response.getContentAsString(), expected);
            assertEquals(HttpStatus.OK.value(), response.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCurrentLinesForPosition(){
        List<Line> expected = new ArrayList<>();
        expected.add(new Line(2, "S2"));

        try{
            String expectedAsJson = new ObjectMapper().writeValueAsString(expected);

            Mockito.when(lineAtStopService.getCurrentLinesForPosition(2,2, "10:00:00"))
                    .thenReturn(expected);

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get("/lines")
                    .param("x", "2")
                    .param("y", "2")
                    .param("timestamp", "10:00:00")
                    .accept(MediaType.APPLICATION_JSON);

            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            MockHttpServletResponse response = result.getResponse();

            assertEquals(response.getContentAsString(), expectedAsJson);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
