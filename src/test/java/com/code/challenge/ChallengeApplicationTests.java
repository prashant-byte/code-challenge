package com.code.challenge;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ChallengeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public static void setUp() throws Exception {

	}

	@AfterClass
	public static void tearDown() throws Exception {}

	@Test
	void contextLoads() {
	}


	@Test
	public void ChallengeControllerHealthCheck() throws Exception {
		Map<String, Object> mp = new HashMap<>();
		java.sql.Date d = new java.sql.Date(123);
		mp.put("response", d);
		ResponseEntity<Map<String, Object>> re = new ResponseEntity<Map<String, Object>>(mp, HttpStatus.OK);

		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("response")));
	}

	@Test
	public void citiesConnected() throws Exception {

		this.mockMvc.perform(get("/connected").param("origin", "Boston")
				.param("destination", "Newark")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Yes")));


		this.mockMvc.perform(get("/connected").param("origin", "Boston")
				.param("destination", "Philadelphia")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Yes")));


		this.mockMvc.perform(get("/connected").param("origin", "Philadelphia")
				.param("destination", "Albany")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("No")));

		this.mockMvc.perform(get("/connected").param("origin", "Miami")
				.param("destination", "SFO")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("No")));
	}

}
