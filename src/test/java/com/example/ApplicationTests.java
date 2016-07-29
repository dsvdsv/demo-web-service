package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ApplicationTests {

	private final RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

	private final String baseUrl = "http://localhost:8080/endpoint";

	@Test(expected = HttpClientErrorException.class)
	public void getMethodNotAllowedTest(){
		template.getForEntity(baseUrl, String.class);
	}

	@Test
	public void registerTest() {
		String request =
						"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
						"<request>" +
							"<request-type>CREATE-AGT</request-type>" +
							"<extra name=\"login\">123456</extra>" +
							"<extra name=\"password\">pwd</extra>" +
						"</request>";

		String expectedResponse =
				"<?xml version='1.0' encoding='UTF-8'?>" +
				"<response>" +
					"<result-code>0</result-code>" +
					"<extra/>"+
				"</response>";

		String actualResponse = template.postForObject(baseUrl, request, String.class);

		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void registerAlreadyExistTest() {
		String request =
						"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
						"<request>" +
							"<request-type>CREATE-AGT</request-type>" +
							"<extra name=\"login\">123456</extra>" +
							"<extra name=\"password\">pwd</extra>" +
						"</request>";

		String expectedResponse =
						"<?xml version='1.0' encoding='UTF-8'?>" +
						"<response>" +
							"<result-code>1</result-code>" +
							"<extra/>"+
						"</response>";

		template.postForObject(baseUrl, request, String.class);

		String actualResponse = template.postForObject(baseUrl, request, String.class);

		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void registerIncorrectParamTest() {
		String request =
						"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
						"<request>" +
							"<request-type>CREATE-AGT</request-type>" +
							"<extra name=\"\">123456</extra>" +
							"<extra name=\"pwd\">pwd</extra>" +
						"</request>";

		String expectedResponse =
						"<?xml version='1.0' encoding='UTF-8'?>" +
						"<response>" +
							"<result-code>2</result-code>" +
							"<extra/>"+
						"</response>";

		String actualResponse = template.postForObject(baseUrl, request, String.class);

		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void invalidRequestTypeTest() {
		String request =
						"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
						"<request>" +
							"<request-type>CREATE-AGTT</request-type>" +
							"<extra name=\"login\">123456</extra>" +
							"<extra name=\"password\">pwd</extra>" +
						"</request>";

		String expectedResponse =
						"<?xml version='1.0' encoding='UTF-8'?>" +
						"<response>" +
							"<result-code>2</result-code>" +
							"<extra/>"+
						"</response>";

		String actualResponse = template.postForObject(baseUrl, request, String.class);


		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void balanceUserNotExistTest() {
		String request =
						"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
						"<request>" +
							"<request-type>GET-BALANCE</request-type>" +
							"<extra name=\"login\">123456</extra>" +
							"<extra name=\"password\">pwd</extra>" +
						"</request>";

		String expectedResponse =
						"<?xml version='1.0' encoding='UTF-8'?>" +
						"<response>" +
							"<result-code>3</result-code>" +
							"<extra/>"+
						"</response>";


		String actualResponse = template.postForObject(baseUrl, request, String.class);


		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void balanceIncorrectParamTest() {
		String request =
						"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
						"<request>" +
							"<request-type>GET-BALANCE</request-type>" +
							"<extra name=\"login\">123456</extra>" +
							"<extra name=\"pwd\">pwd</extra>" +
						"</request>";

		String expectedResponse =
						"<?xml version='1.0' encoding='UTF-8'?>" +
						"<response>" +
							"<result-code>2</result-code>" +
							"<extra/>"+
						"</response>";


		String actualResponse = template.postForObject(baseUrl, request, String.class);


		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void balanceIncorrectPasswordTest() {
		String registerRequest =
						"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
						"<request>" +
							"<request-type>CREATE-AGT</request-type>" +
							"<extra name=\"login\">123456</extra>" +
							"<extra name=\"password\">pwd</extra>" +
						"</request>";

		template.postForObject(baseUrl, registerRequest, String.class);

		String request =
						"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
						"<request>" +
							"<request-type>GET-BALANCE</request-type>" +
							"<extra name=\"login\">123456</extra>" +
							"<extra name=\"password\">pwd1</extra>" +
						"</request>";

		String expectedResponse =
						"<?xml version='1.0' encoding='UTF-8'?>" +
						"<response>" +
							"<result-code>4</result-code>" +
							"<extra/>"+
						"</response>";


		String actualResponse = template.postForObject(baseUrl, request, String.class);


		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void balanceTest() {
		String registerRequest =
						"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
						"<request>" +
							"<request-type>CREATE-AGT</request-type>" +
							"<extra name=\"login\">123456</extra>" +
							"<extra name=\"password\">pwd</extra>" +
						"</request>";

		template.postForObject(baseUrl, registerRequest, String.class);

		String request =
						"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
						"<request>" +
							"<request-type>GET-BALANCE</request-type>" +
							"<extra name=\"login\">123456</extra>" +
							"<extra name=\"password\">pwd</extra>" +
						"</request>";

		String expectedResponse =
						"<?xml version='1.0' encoding='UTF-8'?>" +
						"<response>" +
							"<result-code>0</result-code>" +
							"<extra name=\"balance\">0.0</extra>"+
						"</response>";


		String actualResponse = template.postForObject(baseUrl, request, String.class);


		assertEquals(expectedResponse, actualResponse);
	}
}
