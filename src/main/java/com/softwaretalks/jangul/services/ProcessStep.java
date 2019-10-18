package com.softwaretalks.jangul.services;

public interface ProcessStep {
    void process(HealthcheckResult result);
    int getOrder();
}
