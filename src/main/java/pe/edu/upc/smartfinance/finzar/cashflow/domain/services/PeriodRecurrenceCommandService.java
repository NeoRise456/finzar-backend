package pe.edu.upc.smartfinance.finzar.cashflow.domain.services;

import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.SeedPeriodRecurrencesCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.SeedTransactionTypesCommand;

public interface PeriodRecurrenceCommandService {

        void handle(SeedPeriodRecurrencesCommand command);
}
