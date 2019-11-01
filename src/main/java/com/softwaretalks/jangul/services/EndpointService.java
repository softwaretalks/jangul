package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.User;
import com.softwaretalks.jangul.repositories.EndpointRepository;
import com.softwaretalks.jangul.security.JwtUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EndpointService {

    private Log log = LogFactory.getLog(EndpointService.class);

    @Autowired
    private EndpointRepository endpointRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Endpoint createEndpoints(Endpoint endpoint) {

        try {
            User user = jwtUtil.getCurrentUser();
            endpoint.setUserId(user.getId());
            return endpointRepository.save(endpoint);
        } catch (NotFoundUserException e) {
            log.error(e.getMessage());
            return endpoint;
        }

    }

    public List<Endpoint> getEndpoints()  {

        try {
            User user = jwtUtil.getCurrentUser();
            return endpointRepository.findByUserId(user.getId());
        } catch (NotFoundUserException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }

    }

}
