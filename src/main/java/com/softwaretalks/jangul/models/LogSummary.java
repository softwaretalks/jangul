package com.softwaretalks.jangul.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class LogSummary {

    @Id
    private UUID id;


    @ManyToOne
    @JoinColumn(name="endpoint_id")
    private Endpoint endpoint;

    private Date createDate;
    private Date fromDate;
    private Date toDate;
    private boolean isUp;

    public LogSummary(Endpoint endpoint, Date fromDate, Date toDate, boolean isUp) {
        this.id = UUID.randomUUID();
        this.createDate = Date.from(Instant.now());
        this.endpoint = endpoint;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isUp = isUp;
    }
}


