package com.softwaretalks.jangul.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Endpoint {
    @Id
    private UUID id;
    private final String address;
    private final EndpointProtocol protocol;
    private UUID userId;

    private Endpoint() {
        this.id = UUID.randomUUID();
        address = null;
        protocol = null;
        userId = null;
    }

    public Endpoint(String address, EndpointProtocol protocol,UUID userId) {
        this.id = UUID.randomUUID();
        this.address = address;
        this.protocol = protocol;
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public EndpointProtocol getProtocol() {
        return protocol;
    }

    public static Endpoint httpFrom(String address,UUID userId) {
        return new Endpoint(address, EndpointProtocol.HTTP,userId);
    }

    public UUID getId() {
        return id;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}


