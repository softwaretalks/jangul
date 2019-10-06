package com.softwaretalks.jangul.services;

public class HealthcheckResult {
    private boolean up;

    public HealthcheckResult(boolean up) {
        this.up = up;
    }

    public boolean isUp() {
        return up;
    }
}
