package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.models.Log;
import com.softwaretalks.jangul.repositories.LogRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogProcessStep implements ProcessStep {

    private final LogRepository logRepository;

    public LogProcessStep(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void process(HealthcheckResult result) {
        final var endpoint = result.getEndpoint();
        final List<Log> endpointLogs = logRepository.findByEndpointIdOrderByStartTimeDesc(endpoint.getId());
        if (endpointLogs.isEmpty()) {
            logRepository.save(new Log(endpoint.getId(), result.isUp()));
            return;
        }
        final Log log = endpointLogs.get(0);
        if (log.isUp() == result.isUp()) {
            return;
        }
        logRepository.save(new Log(endpoint.getId(), result.isUp()));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
