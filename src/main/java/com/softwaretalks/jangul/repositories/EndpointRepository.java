package com.softwaretalks.jangul.repositories;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EndpointRepository extends JpaRepository<Endpoint, UUID> {

	List<Endpoint> findByOwner(User owner);

}
