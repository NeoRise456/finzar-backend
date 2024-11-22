package pe.edu.upc.smartfinance.finzar.transactions.domain.services;


import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateTransactionToWalletCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.DeleteTransactionCommand;


public interface TransactionCommandService {
    Long handle(CreateTransactionToWalletCommand command);
    Boolean handle(DeleteTransactionCommand command);


}
