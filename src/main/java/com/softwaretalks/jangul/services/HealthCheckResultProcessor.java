package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.services.impl.HealthcheckResult;

public interface HealthCheckResultProcessor {
    void process(HealthcheckResult result);
}
