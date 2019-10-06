package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.EndpointProtocol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HttpHealthCheckerTest {
    @Mock
    private RestTemplate restTemplate;

    @Test
    public void healthcheck_shouldThrowExceptionOnInvalidEndpoints() {
        HttpHealthChecker healthChecker = new HttpHealthChecker(restTemplate);
        final var tcpEndpoint = new Endpoint("address", EndpointProtocol.TCP);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                healthChecker.healthcheck(tcpEndpoint));
    }

    @Test
    public void healthCheck_shouldReturnDownIfEndpointReturnNon2xxResponse() {
        HttpHealthChecker healthChecker = new HttpHealthChecker(restTemplate);
        final var httpEndpoint = new Endpoint("x", EndpointProtocol.HTTP);

        when(restTemplate.getForEntity(httpEndpoint.getAddress(), String.class))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_GATEWAY));

        final var healthcheckResult = healthChecker.healthcheck(httpEndpoint);

        assertThat(healthcheckResult.isUp()).isFalse();
    }

    @Test
    public void healthcheck_shouldReturnUpIfEndpointReturn2xxResponse() {
        HttpHealthChecker healthChecker = new HttpHealthChecker(restTemplate);
        final var httpEndpoint = new Endpoint("x", EndpointProtocol.HTTP);

        when(restTemplate.getForEntity(httpEndpoint.getAddress(), String.class))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        final var healthcheckResult = healthChecker.healthcheck(httpEndpoint);

        assertThat(healthcheckResult.isUp()).isTrue();
    }

}