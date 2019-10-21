package com.softwaretalks.jangul.web.controllers;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.repositories.EndpointRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EndpointController {
    private final EndpointRepository endpointRepository;

    public EndpointController(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
    }

    @PostMapping(path = "/endpoints")
    public Endpoint postEndpoint(@RequestBody Endpoint endpoint) {
        var savedEndpoint = this.endpointRepository.save(endpoint);
        return savedEndpoint;
    }

    @GetMapping(path = "/endpoints")
    public List<Endpoint> getEndpoints() {
        return endpointRepository.findAll();
    }
}
