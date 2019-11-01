package com.softwaretalks.jangul.controllers;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.User;
import com.softwaretalks.jangul.repositories.EndpointRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class EndpointController {
    private final EndpointRepository endpointRepository;

    public EndpointController(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
    }

    @PostMapping(path = "/endpoints")
    public Endpoint postEndpoint(@RequestBody Endpoint endpoint, HttpServletRequest request) {
        var savedEndpoint = this.endpointRepository.save(endpoint);
        return savedEndpoint;
    }

    @GetMapping(path = "/endpoints")
    public List<Endpoint> getEndpoints(@AuthenticationPrincipal User user) {
        log.info("Logged in user is {}", user);
        return endpointRepository.findAll();
    }
}
