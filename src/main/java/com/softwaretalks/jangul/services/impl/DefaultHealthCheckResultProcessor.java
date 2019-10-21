package com.softwaretalks.jangul.services.impl;

import com.softwaretalks.jangul.services.HealthCheckResultProcessor;
import com.softwaretalks.jangul.services.ProcessStep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultHealthCheckResultProcessor implements HealthCheckResultProcessor {
    private final List<ProcessStep> steps;

    public DefaultHealthCheckResultProcessor(List<ProcessStep> steps) {
        // TODO: consider order
        this.steps = steps;
    }

    @Override
    public void process(HealthcheckResult result) {
        for (ProcessStep step : steps) {
            step.process(result);
        }
    }
}
