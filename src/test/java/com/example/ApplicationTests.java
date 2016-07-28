package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

	private final RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

	private final String baseUrl = "http://localhost:8080/endpoint";

	private final String createReq =
			"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
			"<request>\n" +
			"  <request-type>CREATE-AGT</request-type>\n" +
			"  <extra name=\"login\">123456</extra>\n" +
			"  <extra name=\"password\">pwd</extra>\n" +
			"</request>\n";

	private final String balanceReq =
			"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
			"<request>\n" +
			"  <request-type>GET-BALANCE</request-type>\n" +
			"  <extra name=\"login\">123456</extra>\n" +
			"  <extra name=\"password\">pwd</extra>\n" +
			"</request>\n";

	@Test(expected = HttpClientErrorException.class)
	public void getMethodNotAllowedTest(){
		template.getForEntity(baseUrl, String.class);
	}

	@Test
	public void createRequestTest() {
		String actualResponse = template.postForObject(baseUrl, createReq, String.class);

		String expectedResponse =
				"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
				"<response>\n" +
				"  <result-code>0</result-code>\n" +
				"</response>\n";

		assertEquals(expectedResponse, actualResponse);

	}

	@Test
	public void balanceRequestTest() {
		String actualResponse = template.postForObject(baseUrl, balanceReq, String.class);

		String expectedResponse =
				"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
				"<response>\n" +
				"  <result-code>0</result-code>\n" +
				"  <extra name=\"balance\">0</extra>\n" +
				"</response>\n";

		assertEquals(expectedResponse, actualResponse);
	}

}
