package com.softwaretalks.jangul.models;

import com.softwaretalks.jangul.services.impl.HealthcheckResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor
public class EndpointCheckResult {
    
    @Id
    private UUID id;
    @ManyToOne
    private Endpoint endpoint;
    private boolean up;
    private Date date;
    private boolean isProcessed;


    public EndpointCheckResult(Endpoint endpoint, HealthcheckResult result) {
        this.id = UUID.randomUUID();
        this.endpoint = endpoint;
        this.up = result.isUp();
        this.date = new Date();
        this.isProcessed  = false;
    }
}
