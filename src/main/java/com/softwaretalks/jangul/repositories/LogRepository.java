package com.softwaretalks.jangul.repositories;

import com.softwaretalks.jangul.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LogRepository extends JpaRepository<Log, UUID> {
    List<Log> findByEndpointIdOrderByStartTimeDesc(UUID endpointId);

    Optional<Log> findTopByEndpointIdOrderByStartTimeDesc(UUID endpointId);
}
