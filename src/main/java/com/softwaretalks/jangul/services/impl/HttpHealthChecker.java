package com.softwaretalks.jangul.services.impl;

import com.softwaretalks.jangul.exceptions.UnsuccessfulCheckException;
import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.enums.EndpointProtocol;
import com.softwaretalks.jangul.services.HealthChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Component
public class HttpHealthChecker implements HealthChecker {
    private final RestTemplate restTemplate;

    public HttpHealthChecker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public HealthcheckResult healthcheck(Endpoint endpoint) throws UnsuccessfulCheckException {
        if (endpoint.getProtocol() != getSupportedProtocol()) {
            throw new IllegalArgumentException(
                    String.format("Unsupported protocol %s", endpoint.getProtocol())
            );
        }
        try {
            HttpHeaders headers = new HttpHeaders();

            HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

            ResponseEntity<String> result = restTemplate.exchange(URI.create(endpoint.getAddress()), HttpMethod.GET, requestEntity, String.class);

            if (result.getStatusCode().is2xxSuccessful()) {
                return new HealthcheckResult(endpoint, true);
            } else {
                return new HealthcheckResult(endpoint, false);
            }

        } catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException ex) {
            return new HealthcheckResult(endpoint, false);
        } catch (Exception e) {
            throw new UnsuccessfulCheckException(e);
        }
    }

    @Override
    public EndpointProtocol getSupportedProtocol() {
        return EndpointProtocol.HTTP;
    }
}
