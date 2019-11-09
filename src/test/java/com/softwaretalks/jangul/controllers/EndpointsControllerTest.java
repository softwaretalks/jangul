package com.softwaretalks.jangul.controllers;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.EndpointProtocol;
import com.softwaretalks.jangul.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndpointsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

	private final String username = "email@gmail.com";
	private final String password = "password";

    @Test
    public void postEndpoints_shouldReturnSavedEndpoint() {

        final String token = generateToken();

        restTemplate.getRestTemplate().getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + token);
            return execution.execute(request, body);
        });

        String address = "https://softwaretalks.ir";

		User user = new User(username,password);

        final Endpoint endpoint = new Endpoint(address, EndpointProtocol.HTTP,user);
        final Endpoint response = restTemplate.postForObject("/endpoints", endpoint, Endpoint.class);
        assertThat(response.getAddress())
                .isEqualTo(address);

        ResponseEntity<List<Endpoint>> endpointsEntity = restTemplate.exchange("/endpoints",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        final var savedEndpoints = endpointsEntity.getBody();
        assertThat(savedEndpoints.size()).isEqualTo(1);
        assertThat(savedEndpoints.get(0).getAddress()).isEqualTo(address);
    }

    private String generateToken() {
        restTemplate.postForObject("/users", Map.of("email", username, "password", password), Object.class);
        final Map<String, String> tokenMap = restTemplate.postForObject("/tokens", Map.of("username", username, "password", password), Map.class);
        return tokenMap.get("token");
    }
}
