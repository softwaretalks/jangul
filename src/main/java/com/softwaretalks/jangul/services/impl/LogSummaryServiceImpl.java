package com.softwaretalks.jangul.services.impl;

import com.softwaretalks.jangul.controllers.HealthCheckSummaryController;
import com.softwaretalks.jangul.repositories.LogSummaryRepository;
import com.softwaretalks.jangul.services.LogSummaryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogSummaryServiceImpl implements LogSummaryService {

    private LogSummaryRepository logSummaryRepository;

    public LogSummaryServiceImpl(LogSummaryRepository logSummaryRepository) {
        this.logSummaryRepository = logSummaryRepository;
    }

    @Override
    public List<HealthCheckSummaryController.LogSummaryDto> getLogSummariesFromDateToToDate(Date from, Date to) {
        return logSummaryRepository.findAllByCreateDateGreaterThanEqualAndCreateDateLessThanEqualOrderByCreateDateAsc(from, to)
                .stream().map(HealthCheckSummaryController.LogSummaryDto::new).collect(Collectors.toList());
    }
}
