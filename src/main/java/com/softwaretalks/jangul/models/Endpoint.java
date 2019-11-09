package com.softwaretalks.jangul.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Endpoint {
    @Id
    private UUID id;
    private final String address;
    private final EndpointProtocol protocol;

    @ManyToOne
    private User owner;

    private Endpoint() {
        this.id = UUID.randomUUID();
        address = null;
        protocol = null;
    }

    public Endpoint(String address, EndpointProtocol protocol,User owner) {
        this.id = UUID.randomUUID();
        this.address = address;
        this.protocol = protocol;
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public EndpointProtocol getProtocol() {
        return protocol;
    }

    public static Endpoint httpFrom(String address,User owner) {
        return new Endpoint(address, EndpointProtocol.HTTP,owner);
    }

    public UUID getId() {
        return id;
    }

    public void setOwner(User owner) {

        this.owner = owner;
    }
}
