package com.code.challenge.controller;

import com.code.challenge.Service.ChallengeService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * Code challenge controller class.
 *
 * @author  Prashant Kumar
 * @version 1.0
 * @since   2020-07-15
 */
@Controller
@RequestMapping("/")
public class ChallengeController {
    private static final Logger logger = LoggerFactory.getLogger(ChallengeController.class);


    @Autowired
    private ChallengeService challengeService;

    @PostConstruct
    public void setUp() throws Exception {
        logger.info("Challenge Controller Starting");
    }

    @PreDestroy
    public void cleanUp() {
        logger.info("Challenge Controller Shutting down...");
    }

    @ApiOperation(value = "basic health check")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> healthCheck(HttpServletRequest request) {
        return challengeService.healthCheck();
    }



    @ApiOperation(value = "Determines if two cities are connected or not")
    @RequestMapping(
            value = "/connected",
            method = RequestMethod.GET)
    public ResponseEntity<String> connectedCities(@RequestParam("origin") String origin,
                                                @RequestParam("destination") String destination,
                                                HttpServletRequest request) {
        return challengeService.connectedCities(origin, destination);
    }

}
