package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.controllers.HealthCheckSummaryController;

import java.util.Date;
import java.util.List;

public interface LogSummaryService {

    List<HealthCheckSummaryController.LogSummaryDto> getLogSummariesFromDateToToDate(Date from, Date to);
}
