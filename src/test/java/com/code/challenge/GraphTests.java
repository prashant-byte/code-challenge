package com.code.challenge;
import com.code.challenge.Graph.Graph;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * Code challenge Graph unit tests.
 *
 * @author  Prashant Kumar
 * @version 1.0
 * @since   2020-07-15
 */
public class GraphTests {

    public static Graph testGraph;
    @BeforeClass
    public static void setUp() throws Exception {
        Map<String, Integer> cities = new HashMap<>();
        cities.put("boston", 0);
        cities.put("new york", 1);
        cities.put("philadelphia", 2);
        cities.put("newark", 3);
        cities.put("trenton", 4);
        cities.put("albany", 5);

        testGraph = new Graph(6, cities);

        testGraph.addEdge(0, 3);
        testGraph.addEdge(2, 5);
    }

    @Test
    public void testGraph() {
        assertNotNull(testGraph);
        assertEquals(0, testGraph.cities.get("boston").intValue());
    }

    @Test
    public void testReach() {
        int result = testGraph.reach(0, 3);
        assertEquals(1, result);
        result = testGraph.reach(2, 5);
        assertEquals(1, result);
        result = testGraph.reach(2, 4);
        assertEquals(0, result);
    }
}
