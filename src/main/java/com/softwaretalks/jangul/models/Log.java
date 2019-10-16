package com.softwaretalks.jangul.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class Log {
    @Id
    private UUID id;
    private Date startTime;
    private boolean isUp;
    private UUID endpointId;

    public Log(UUID endpointId, boolean isUp) {
        this.id = UUID.randomUUID();
        this.isUp = isUp;
        this.endpointId = endpointId;
        this.startTime = new Date();
    }

    private Log() {
    }

    public UUID getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public boolean isUp() {
        return isUp;
    }

    public UUID getEndpointId() {
        return endpointId;
    }


}
