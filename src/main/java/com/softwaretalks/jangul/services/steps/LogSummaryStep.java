package com.softwaretalks.jangul.services.steps;

import com.softwaretalks.jangul.models.LogSummary;
import com.softwaretalks.jangul.repositories.LogSummaryRepository;
import com.softwaretalks.jangul.services.ProcessStep;
import com.softwaretalks.jangul.services.impl.HealthcheckResult;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class LogSummaryStep implements ProcessStep {

    private static final int CURRENT_STEP_ORDER = 2;

    private LogSummaryRepository logSummaryRepository;

    public LogSummaryStep(LogSummaryRepository logSummaryRepository) {
        this.logSummaryRepository = logSummaryRepository;
    }

    @Override
    public void process(HealthcheckResult result) {

        Optional<LogSummary> lastLogSummaryOptional = logSummaryRepository.findTop1ByOrderByCreateDateDesc();
        LogSummary lastLogSummary;


        if (lastLogSummaryOptional.isEmpty()) {
            lastLogSummary = new LogSummary(
                    result.getEndpoint(),
                    Date.from(Instant.now()),
                    Date.from(Instant.now().plusSeconds(60L)),
                    result.isUp());
            logSummaryRepository.save(lastLogSummary);
        } else
            lastLogSummary = lastLogSummaryOptional.get();

        if (result.isUp())
            this.updateLogSummaryWhenEndpointIsUp(result, lastLogSummary);
        else
            this.updateLogSummaryWhenEndpointIsDown(result, lastLogSummary);
    }

    private void updateLogSummaryWhenEndpointIsUp(HealthcheckResult result, LogSummary logSummary) {

        if (logSummary.isUp()) {
            logSummary.setToDate(Date.from(Instant.now()));
            logSummaryRepository.save(logSummary);
        } else {
            LogSummary logSummaryReversed = new LogSummary(
                    result.getEndpoint(),
                    logSummary.getToDate(),
                    Date.from(logSummary.getToDate().toInstant().plusSeconds(1)),
                    Boolean.TRUE);
            logSummaryRepository.save(logSummaryReversed);
        }
    }

    private void updateLogSummaryWhenEndpointIsDown(HealthcheckResult result, LogSummary logSummary) {

        if (!logSummary.isUp()) {
            logSummary.setToDate(Date.from(Instant.now()));
            logSummaryRepository.save(logSummary);
        } else {
            LogSummary logSummaryReversed = new LogSummary(
                    result.getEndpoint(),
                    logSummary.getToDate(),
                    Date.from(logSummary.getToDate().toInstant().plusSeconds(1)),
                    Boolean.FALSE);
            logSummaryRepository.save(logSummaryReversed);
        }
    }


    @Override
    public int getOrder() {
        return CURRENT_STEP_ORDER;
    }
}
