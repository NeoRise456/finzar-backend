package pe.edu.upc.smartfinance.finzar.transactions.domain.services;


import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.SeedTransactionTypesCommand;


public interface TransactionTypesCommandService {
    void handle(SeedTransactionTypesCommand command);
}
