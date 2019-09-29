package com.softwaretalks.jangul.repositories;

import com.softwaretalks.jangul.models.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EndpointRepository extends JpaRepository<Endpoint, UUID> {
}
