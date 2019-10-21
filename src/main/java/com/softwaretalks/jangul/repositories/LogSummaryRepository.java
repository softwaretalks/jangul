package com.softwaretalks.jangul.repositories;

import com.softwaretalks.jangul.models.LogSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LogSummaryRepository extends JpaRepository<LogSummary, UUID> {

    Optional<LogSummary> findTop1ByOrderByCreateDateDesc();

    List<LogSummary>
    findAllByCreateDateGreaterThanEqualAndCreateDateLessThanEqualOrderByCreateDateAsc(Date from, Date to);

}
