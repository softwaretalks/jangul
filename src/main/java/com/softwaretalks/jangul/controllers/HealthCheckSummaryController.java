package com.softwaretalks.jangul.controllers;

import com.softwaretalks.jangul.models.LogSummary;
import com.softwaretalks.jangul.services.LogSummaryService;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class HealthCheckSummaryController {

    private LogSummaryService logSummaryService;

    public HealthCheckSummaryController(LogSummaryService logSummaryService) {
        this.logSummaryService = logSummaryService;
    }

    @GetMapping("/summary/{from}/{to}")
    public List<LogSummaryDto> getLogSummaryFromDateToDate(
            @PathVariable("from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fromDate,
            @PathVariable("to") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date toDate) {

        return logSummaryService.getLogSummariesFromDateToToDate(fromDate, toDate);

    }

    @Getter
    public static class LogSummaryDto {
        private String address;
        private ZonedDateTime from;
        private ZonedDateTime to;
        private boolean isUp;

        public LogSummaryDto(LogSummary logSummary){
            this.address = logSummary.getEndpoint().getAddress();
            this.from = ZonedDateTime.ofInstant(logSummary.getFromDate().toInstant(), ZoneId.of("Asia/Tehran"));
            this.to   = ZonedDateTime.ofInstant(logSummary.getToDate().toInstant(), ZoneId.of("Asia/Tehran"));
            this.isUp = logSummary.isUp();
        }

    }
}
