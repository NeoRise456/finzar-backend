package pe.edu.upc.smartfinance.finzar.transactions.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.SeedTransactionTypesCommand;

@Service
public interface TransactionTypesCommandService {
    void handle(SeedTransactionTypesCommand command);
}
