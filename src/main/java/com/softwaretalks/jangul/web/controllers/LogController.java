package com.softwaretalks.jangul.web.controllers;

import com.softwaretalks.jangul.models.Log;
import com.softwaretalks.jangul.repositories.LogRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class LogController {

    private final LogRepository logRepository;

    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping("/endpoints/{endpoint-id}/logs")
    public List<Log> getLogs(@PathVariable("endpoint-id") UUID endpointId) {
        return logRepository.findByEndpointIdOrderByStartTimeDesc(endpointId);
    }
}
