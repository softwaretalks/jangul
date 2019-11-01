package com.softwaretalks.jangul.controllers;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.services.EndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EndpointController {

    @Autowired
    private EndpointService endpointService;

    @PostMapping(path = "/endpoints")
    public Endpoint postEndpoint(@RequestBody Endpoint endpoint) {
        return endpointService.createEndpoints(endpoint);
    }

    @GetMapping(path = "/endpoints")
    public List<Endpoint> getEndpoints() {
        return endpointService.getEndpoints();
    }
}
