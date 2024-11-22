package pe.edu.upc.smartfinance.finzar.transactions.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.SeedTransactionTypesCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.TransactionTypesCommandService;

import java.sql.Timestamp;

@Service
public class TransactionsReadyEventHandler {

    private final TransactionTypesCommandService transactionTypesCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionsReadyEventHandler.class);

    public TransactionsReadyEventHandler(TransactionTypesCommandService transactionTypesCommandService) {
        this.transactionTypesCommandService = transactionTypesCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if transaction types seeding is needed for {} at {}",
                applicationName, currentTimestamp());

        transactionTypesCommandService.handle(new SeedTransactionTypesCommand());
        LOGGER.info("Transaction types seeding verification finished for {} at {}",
                applicationName, currentTimestamp());

    }


    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

}
