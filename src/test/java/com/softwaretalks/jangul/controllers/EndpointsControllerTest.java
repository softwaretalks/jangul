package com.softwaretalks.jangul.controllers;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.EndpointProtocol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndpointsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @WithMockUser(value = "user")
    public void postEndpoints_shouldReturnSavedEndpoint() {
        String address = "https://softwaretalks.ir";
        final Endpoint endpoint = new Endpoint(address, EndpointProtocol.HTTP);
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
}
