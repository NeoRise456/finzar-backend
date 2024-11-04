package pe.edu.upc.smartfinance.finzar.transactions.domain.services;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Transaction;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.DeleteTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.UpdateTransactionCommand;

import java.util.Optional;

public interface TransactionCommandService {
    Long handle(CreateTransactionCommand command);
    Boolean handle(DeleteTransactionCommand command);
    Optional<Transaction> handle(UpdateTransactionCommand command);
}
