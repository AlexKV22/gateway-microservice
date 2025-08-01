package org.example.gateway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled
class GatewayApplicationTests {

	private final TestRestTemplate restTemplate;

	@Autowired
	public GatewayApplicationTests(TestRestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Test
	void contextLoads() throws InterruptedException {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8888/config-server/default", String.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Thread.sleep(14000);
		ResponseEntity<String> responseEureka = restTemplate.getForEntity("http://localhost:8761/eureka/apps", String.class);
		Assertions.assertEquals(HttpStatus.OK, responseEureka.getStatusCode());
		Assertions.assertTrue(responseEureka.getBody().toLowerCase().contains("gateway"));
	}

}
