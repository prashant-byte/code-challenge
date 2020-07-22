package com.code.challenge;

import com.code.challenge.Graph.Graph;
import com.code.challenge.Service.ChallengeService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

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
        ChallengeService challengeService = new ChallengeService();
        ResponseEntity<Map<String, Object>> response = challengeService.healthCheck();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().containsKey("response"));
    }

    @Test
    public void testConnectedCities() throws Exception{
        // Create mock
        Map<String, Integer> mockCities = new HashMap<>();
        mockCities.put("boston", 0);
        mockCities.put("new york", 1);
        mockCities.put("philadelphia", 2);
        mockCities.put("newark", 3);
        mockCities.put("trenton", 4);
        mockCities.put("albany", 5);

        Graph graph = mock(Graph.class);
        lenient().when(graph.reach(anyInt(), anyInt())).thenReturn(1);
        lenient().when(graph.getCities()).thenReturn(mockCities);

        ChallengeService challengeService = mock(ChallengeService.class);
        lenient().when(challengeService.getCityGraph()).thenReturn(graph);
        lenient().when(challengeService.connectedCities(any(), any())).thenCallRealMethod();

        ResponseEntity<String> response = challengeService.connectedCities("Boston", "Newark");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Yes"));

        response = challengeService.connectedCities("Boston", "Philadelphia");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Yes"));

        lenient().when(graph.reach(anyInt(), anyInt())).thenReturn(0);

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

    @Test
    public void testCreateGraph() throws Exception{
        ChallengeService challengeService = new ChallengeService();
        challengeService.createGraph();
        assertNotNull(challengeService.getCityGraph());
    }
}
