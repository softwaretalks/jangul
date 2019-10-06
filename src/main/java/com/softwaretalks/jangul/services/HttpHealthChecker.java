package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.EndpointProtocol;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpHealthChecker implements HealthChecker {
    private final RestTemplate restTemplate;
    private final HealthcheckResult successfulResult = new HealthcheckResult(true);
    private final HealthcheckResult failedResult = new HealthcheckResult(false);

    public HttpHealthChecker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public HealthcheckResult healthcheck(Endpoint endpoint) {
        if (endpoint.getProtocol() != getSupportedProtocol()) {
            throw new IllegalArgumentException(
                    String.format("Unsupported protocol %s", endpoint.getProtocol())
            );
        }
        try {
            final var returnedEntity = this.restTemplate.getForEntity(endpoint.getAddress(), String.class);
            if (returnedEntity.getStatusCode().is2xxSuccessful()) {
                return successfulResult;
            }
            return failedResult;

        } catch (HttpClientErrorException ex) {
            return failedResult;
        }
    }

    @Override
    public EndpointProtocol getSupportedProtocol() {
        return EndpointProtocol.HTTP;
    }
}
