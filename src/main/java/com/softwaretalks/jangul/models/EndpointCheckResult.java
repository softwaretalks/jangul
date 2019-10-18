package com.softwaretalks.jangul.models;

import com.softwaretalks.jangul.services.HealthcheckResult;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Entity
public class EndpointCheckResult {
    @Id
    private UUID id;

    @ManyToOne
    private Endpoint endpoint;

    private boolean up;

    private Date date;

    private EndpointCheckResult() {
    }

    public EndpointCheckResult(Endpoint endpoint, HealthcheckResult result) {
        this.id = UUID.randomUUID();
        this.endpoint = endpoint;
        this.up = result.isUp();
        this.date = new Date();
    }
}
