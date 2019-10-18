package com.softwaretalks.jangul.repositories;

import com.softwaretalks.jangul.models.EndpointCheckResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EndpointCheckResultRepository extends JpaRepository<EndpointCheckResult, UUID> {
}
