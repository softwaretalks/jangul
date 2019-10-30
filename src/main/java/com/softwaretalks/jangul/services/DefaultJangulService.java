package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.EndpointCheckResult;
import com.softwaretalks.jangul.models.EndpointProtocol;
import com.softwaretalks.jangul.repositories.EndpointCheckResultRepository;
import com.softwaretalks.jangul.repositories.EndpointRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultJangulService implements JangulService {
    private final EndpointRepository endpointRepository;
    private final Map<EndpointProtocol, HealthChecker> healthCheckers;
    private final EndpointCheckResultRepository endpointCheckResultRepository;
    private final HealthCheckResultProcessor processor;

    public DefaultJangulService(
            EndpointRepository endpointRepository,
            List<HealthChecker> healthCheckers,
            EndpointCheckResultRepository endpointCheckResultRepository,
            HealthCheckResultProcessor processor
    ) {
        this.endpointRepository = endpointRepository;
        this.healthCheckers = healthCheckers.stream().collect(
                Collectors.toMap(HealthChecker::getSupportedProtocol, Function.identity())
        );
        this.endpointCheckResultRepository = endpointCheckResultRepository;
        this.processor = processor;
    }

    @Override
    @Scheduled(fixedDelay = 1 * 1000)
    public void runHealthchecks() {
        log.info("runHealthchecks");
        endpointRepository.findAll().stream()
                .forEach(this::processEndpoint);
    }

    private void processEndpoint(Endpoint endpoint) {
        final var checker = this.healthCheckers.get(endpoint.getProtocol());
        if (checker == null) {
            log.warn("Endpoint protocol {} is not supported! ", endpoint.getProtocol());
            return;
        }

        final HealthcheckResult healthcheckResult;
        try {
            healthcheckResult = checker.healthcheck(endpoint);
            final EndpointCheckResult endpointCheckResult = new EndpointCheckResult(endpoint, healthcheckResult);
            endpointCheckResultRepository.save(endpointCheckResult);
            processor.process(healthcheckResult);
        } catch (UnsuccessfulCheckException e) {
            log.warn(String.format("Error in health checking endpoint %s", endpoint.getAddress()), e);
        }
    }
}
