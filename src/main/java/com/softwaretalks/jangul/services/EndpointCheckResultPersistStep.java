package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.models.EndpointCheckResult;
import com.softwaretalks.jangul.repositories.EndpointCheckResultRepository;
import org.springframework.stereotype.Component;

@Component
public class EndpointCheckResultPersistStep implements ProcessStep {
    private final EndpointCheckResultRepository endpointCheckResultRepository;

    public EndpointCheckResultPersistStep(EndpointCheckResultRepository endpointCheckResultRepository) {
        this.endpointCheckResultRepository = endpointCheckResultRepository;
    }

    @Override
    public void process(HealthcheckResult result) {
        final EndpointCheckResult endpointCheckResult = new EndpointCheckResult(result);
        endpointCheckResultRepository.save(endpointCheckResult);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
