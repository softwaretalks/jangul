package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.User;
import com.softwaretalks.jangul.repositories.EndpointRepository;
import com.softwaretalks.jangul.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndpointService {

    @Autowired
    private EndpointRepository endpointRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Endpoint createEndpoints(Endpoint endpoint) {
        User user = jwtUtil.getCurrentUser();
        endpoint.setUserId(user.getId());
        return endpointRepository.save(endpoint);
    }

    public List<Endpoint> getEndpoints() {
        User user = jwtUtil.getCurrentUser();
        return endpointRepository.findByUserId(user.getId());
    }

}
