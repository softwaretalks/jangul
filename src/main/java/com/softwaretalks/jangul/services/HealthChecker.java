package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.EndpointProtocol;

public interface HealthChecker {
    HealthcheckResult healthcheck(Endpoint endpoint);

    EndpointProtocol getSupportedProtocol();
}
