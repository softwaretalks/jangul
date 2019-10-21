package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.exceptions.UnsuccessfulCheckException;
import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.enums.EndpointProtocol;
import com.softwaretalks.jangul.services.impl.HealthcheckResult;

public interface HealthChecker {
    HealthcheckResult healthcheck(Endpoint endpoint) throws UnsuccessfulCheckException;

    EndpointProtocol getSupportedProtocol();
}
