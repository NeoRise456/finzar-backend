package pe.edu.upc.smartfinance.finzar.cashflow.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.SeedPeriodRecurrencesCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.PeriodRecurrenceCommandService;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.SeedTransactionTypesCommand;

import java.sql.Timestamp;

@Service
public class PeriodRecurrenceReadyHandler {

    private final PeriodRecurrenceCommandService periodRecurrenceCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodRecurrenceReadyHandler.class);

    public PeriodRecurrenceReadyHandler(PeriodRecurrenceCommandService periodRecurrenceCommandService) {
        this.periodRecurrenceCommandService = periodRecurrenceCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if Period Recurrences seeding is needed for {} at {}",
                applicationName, currentTimestamp());

        periodRecurrenceCommandService.handle(new SeedPeriodRecurrencesCommand());
        LOGGER.info("Transaction types seeding verification finished for {} at {}",
                applicationName, currentTimestamp());

    }


    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }


}
