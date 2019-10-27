package com.softwaretalks.jangul.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Entity
public class Endpoint {
    @Id
    private UUID id;
    @Pattern(regexp = "^(https?://)?(www\\.)?([\\w]+\\.)+[\\w]{2,63}/?$", message = "invalid address")
    @NotNull(message = "address cannot be null")
    private final String address;
    @NotNull(message = "protocol cannot be null")
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
