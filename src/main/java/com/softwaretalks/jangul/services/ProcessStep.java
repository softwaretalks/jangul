package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.services.impl.HealthcheckResult;

public interface ProcessStep {
    void process(HealthcheckResult result);
    int getOrder();
}
