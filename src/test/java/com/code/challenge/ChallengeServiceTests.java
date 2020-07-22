package com.code.challenge;

import com.code.challenge.Service.ChallengeService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.Assert.*;

/**
 *
 * Code challenge service unit tests.
 *
 * @author  Prashant Kumar
 * @version 1.0
 * @since   2020-07-15
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTests {

    @InjectMocks
    private static ChallengeService challengeService;

    @BeforeClass
    public static void setUp() throws Exception {
    }

    @AfterClass
    public static void tearDown() throws Exception {}

    @Test
    void contextLoads() {
    }

    @Test
    public void testHealthCheck() throws Exception{
        ResponseEntity<Map<String, Object>> response = challengeService.healthCheck();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().containsKey("response"));
    }

    @Test
    public void testConnectedCities() throws Exception{
        challengeService.createGraph();
        ResponseEntity<String> response = challengeService.connectedCities("Boston", "Newark");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Yes"));

        response = challengeService.connectedCities("Boston", "Philadelphia");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Yes"));

        response = challengeService.connectedCities("Philadelphia", "Albany");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("No"));

        response = challengeService.connectedCities("Miami", "SFO");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("No"));

        response = challengeService.connectedCities(null, "SFO");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("No"));

        response = challengeService.connectedCities("Miami", null);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("No"));
    }
}
