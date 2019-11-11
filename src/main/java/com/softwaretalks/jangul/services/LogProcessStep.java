package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.models.Log;
import com.softwaretalks.jangul.repositories.LogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LogProcessStep implements ProcessStep {

    private final LogRepository logRepository;

    public LogProcessStep(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void process(HealthcheckResult result) {
        final var endpoint = result.getEndpoint();
        final Optional<Log> maybeEndpointLog = logRepository.findTopByEndpointIdOrderByStartTimeDesc(endpoint.getId());
        if (maybeEndpointLog.isEmpty()) {
            logRepository.save(new Log(endpoint.getId(), result.isUp()));
            return;
        }
        final Log log = maybeEndpointLog.get();
        if (log.isUp() == result.isUp()) {
            return;
        }
        logRepository.save(new Log(endpoint.getId(), result.isUp()));
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
