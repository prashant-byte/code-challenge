package com.code.challenge.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.code.challenge.Graph.Graph;
import javax.annotation.PostConstruct;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * Code challenge service class.
 *
 * @author  Prashant Kumar
 * @version 1.0
 * @since   2020-07-15
 */
@Service
public class ChallengeService {
    private static final Logger logger = LoggerFactory.getLogger(ChallengeService.class);

    private Graph cityGraph;

    @PostConstruct
    public void setUp() throws Exception {
        logger.info("Challenge Service Starting");
        createGraph();
    }

    /**
     * This is the healthCheck method
     * @return ResponseEntity.
     */
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> mp = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        mp.put("response", formatter.format(date));
        ResponseEntity<Map<String, Object>> re = new ResponseEntity<Map<String, Object>>(mp, HttpStatus.OK);
        return re;
    }

    /**
     * This is the connectedCities method used to check whether 2 cities are connected or not.
     * @param origin Name of the origin city.
     * @param destination Name of the destination city.
     * @return ResponseEntity.
     */
    public ResponseEntity<String> connectedCities(String origin, String destination) {
        int connected = 0;

        try {
            if(cityGraph.cities.containsKey(origin.toLowerCase()) &&
                    cityGraph.cities.containsKey(destination.toLowerCase())) {

                connected = cityGraph.reach(cityGraph.cities.get(origin.toLowerCase()),
                        cityGraph.cities.get(destination.toLowerCase()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseEntity<String> re = new ResponseEntity<>((connected == 1) ? "Yes": "No", HttpStatus.OK);
        return re;
    }


    /**
     * This is the create Graph methos used to create graph of cities.
     * @exception IOException On input error.
     * @see IOException
     */
    public void createGraph() throws IOException {
        logger.info("Creating graph...");
        File file = null;
        // Read file content
        file = ResourceUtils.getFile("classpath:city.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        Map<String, Integer> cities = new HashMap<>();

        int index = 0;
        Map<String, String> cityMap = new HashMap<>();
        String line;

        while ((line = br.readLine()) != null) {
            String[] cityList = line.split(", ");
            cityMap.put(cityList[0].trim().toLowerCase(),  cityList[1].trim().toLowerCase());

            for(String city : cityList) {
                if(!cities.containsKey(city.trim().toLowerCase())) {
                    cities.put(city.trim().toLowerCase(), index++);
                }
            }
        }

        cityGraph = new Graph(cities.size(), cities);
        for (Map.Entry<String, String> entry : cityMap.entrySet()) {
            cityGraph.addEdge(cities.get(entry.getKey()), cities.get(entry.getValue()));
            cityGraph.addEdge(cities.get(entry.getValue()), cities.get(entry.getKey()));

        }
    }

}
