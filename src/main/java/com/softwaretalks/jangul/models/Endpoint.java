package com.softwaretalks.jangul.models;

import com.softwaretalks.jangul.models.enums.EndpointProtocol;

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

    public static Endpoint httpFrom(String address) {
        return new Endpoint(address, EndpointProtocol.HTTP);
    }

    public UUID getId() {
        return id;
    }
}
