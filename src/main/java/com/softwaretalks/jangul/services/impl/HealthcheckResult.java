package com.softwaretalks.jangul.services.impl;

import com.softwaretalks.jangul.models.Endpoint;

public class HealthcheckResult {
    private final Endpoint endpoint;
    private boolean up;

    public HealthcheckResult(Endpoint endpoint, boolean up) {
        this.endpoint = endpoint;
        this.up = up;
    }

    public boolean isUp() {
        return up;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    @Override
    public String toString() {
        return "HealthcheckResult{" +
                "endpoint=" + endpoint.getAddress() +
                ", up=" + up +
                '}';
    }
}
