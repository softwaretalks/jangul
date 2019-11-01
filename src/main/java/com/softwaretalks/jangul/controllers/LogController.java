package com.softwaretalks.jangul.controllers;

import com.softwaretalks.jangul.models.Log;
import com.softwaretalks.jangul.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class LogController {

    @Autowired
    private LogRepository logRepository;

    @GetMapping("/endpoints/{endpoint-id}/logs")
    public List<Log> getLogs(@PathVariable("endpoint-id") UUID endpointId) {
        return logRepository.findByEndpointIdOrderByStartTimeDesc(endpointId);
    }
}
