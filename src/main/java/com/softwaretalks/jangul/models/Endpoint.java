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

    private Endpoint() {
        this.id = UUID.randomUUID();
        address = null;
        protocol = null;
    }

    public Endpoint(String address, EndpointProtocol protocol) {
        this.id = UUID.randomUUID();
        this.address = address;
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public EndpointProtocol getProtocol() {
        return protocol;
    }
}
