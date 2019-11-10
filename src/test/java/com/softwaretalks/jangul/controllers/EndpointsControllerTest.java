package com.softwaretalks.jangul.controllers;


import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.EndpointProtocol;
import com.softwaretalks.jangul.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EndpointsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

	private final String username = "email@gmail.com";
	private final String password = "password";


	@BeforeEach
	public void setup(){
		final String token = generateToken();

		restTemplate.getRestTemplate().getInterceptors().add((request, body, execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + token);
			return execution.execute(request, body);
		});
	}


    @Test
    public void postEndpoints_shouldReturnSavedEndpoint() {

		final String softwaretalksAddress = "https://softwaretalks.ir";
		final User user = new User(username,password);

		final Endpoint response = createEndpoint(softwaretalksAddress, user);
        assertThat(response.getAddress())
                .isEqualTo(softwaretalksAddress);

        ResponseEntity<List<Endpoint>> endpointsEntity = restTemplate.exchange("/endpoints",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        final var savedEndpoints = endpointsEntity.getBody();
        assertThat(savedEndpoints.size()).isEqualTo(1);
        assertThat(savedEndpoints.get(0).getAddress()).isEqualTo(softwaretalksAddress);
    }


	@Test
	public void getEndpoints_shouldReturnTwoEndpoint() {

		final String bingAddress = "https://bing.com";
		final String googleAddress = "https://google.com";

		User user = new User(username,password);

		final Endpoint responseBingEndpoint = createEndpoint(bingAddress, user);
		assertThat(responseBingEndpoint.getAddress())
				.isEqualTo(bingAddress);
		final Endpoint responseGoogleEndpoint = createEndpoint(googleAddress, user);
		assertThat(responseGoogleEndpoint.getAddress())
				.isEqualTo(googleAddress);

		ResponseEntity<List<Endpoint>> endpointsEntity = restTemplate.exchange("/endpoints",
																			   HttpMethod.GET,
																			   null,
																			   new ParameterizedTypeReference<>() {
																			   }
		);

		final var savedEndpoints = endpointsEntity.getBody();
		assertThat(savedEndpoints.size()).isEqualTo(2);
		assertThat(savedEndpoints.get(0).getAddress()).isEqualTo(bingAddress);
		assertThat(savedEndpoints.get(1).getAddress()).isEqualTo(googleAddress);

	}


	@Test
	public void getEndpoints_shouldReturnEmptyListEndpoint() {

		ResponseEntity<List<Endpoint>> endpointsEntity = restTemplate.exchange("/endpoints",
																			   HttpMethod.GET,
																			   null,
																			   new ParameterizedTypeReference<>() {
																			   }
		);

		final var savedEndpoints = endpointsEntity.getBody();
		assertThat(savedEndpoints.size()).isEqualTo(0);
	}

	private Endpoint createEndpoint(String bingAddress, User user) {

		final Endpoint bingEndpoint = new Endpoint(bingAddress, EndpointProtocol.HTTP, user);
		return restTemplate.postForObject("/endpoints", bingEndpoint, Endpoint.class);

	}

	private String generateToken() {
        restTemplate.postForObject("/users", Map.of("email", username, "password", password), Object.class);
        final Map<String, String> tokenMap = restTemplate.postForObject("/tokens", Map.of("username", username, "password", password), Map.class);
        return tokenMap.get("token");
    }
}
