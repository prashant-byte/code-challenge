package com.code.challenge;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.code.challenge.Service.ChallengeService;
import com.code.challenge.controller.ChallengeController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Code challenge controller unit tests.
 *
 * @author  Prashant Kumar
 * @version 1.0
 * @since   2020-07-15
 */
@WebMvcTest(ChallengeController.class)
public class ChallengeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ChallengeService service;


    @Test
    public void testHealthCheck() throws Exception {
        long timestamp = 123;
        Date testDate = new Date(timestamp * 1000);
        Map<String, Object> mp = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = testDate;
        mp.put("response", formatter.format(date));
        ResponseEntity<Map<String, Object>> re = new ResponseEntity<Map<String, Object>>(mp, HttpStatus.OK);
        when(service.healthCheck()).thenReturn(re);
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("response")))
                .andExpect(content().string(containsString("1969")));
    }

    @Test
    public void testConnectedCities() throws Exception {
        ResponseEntity<String> re = new ResponseEntity<String>("Yes", HttpStatus.OK);

        when(service.connectedCities("Boston", "Newark")).thenReturn(re);

        this.mockMvc.perform(get("/connected").param("origin", "Boston")
                .param("destination", "Newark")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Yes")));
    }
}
