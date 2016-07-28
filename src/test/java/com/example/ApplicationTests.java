package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

	private final RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

	private final String baseUrl = "http://localhost:8080/endpoint";
	@Test
	public void contextLoads() {
		String s = template.postForObject(baseUrl, "", String.class);

		assertEquals("Bye World", s);
	}

}
