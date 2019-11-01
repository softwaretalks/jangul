package com.softwaretalks.jangul.repositories;

import com.softwaretalks.jangul.models.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint, UUID> {

    List<Endpoint> findByUserId(UUID userId);

}
