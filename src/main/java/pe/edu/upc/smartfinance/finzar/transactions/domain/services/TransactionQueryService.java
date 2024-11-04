package pe.edu.upc.smartfinance.finzar.transactions.domain.services;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Transaction;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.queries.GetTransactionByIdQuery;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.queries.GetTransactionsByWalletIdQuery;

import java.util.List;
import java.util.Optional;

public interface TransactionQueryService {
    Optional<Transaction> handle(GetTransactionByIdQuery query);
    List<Transaction> handle(GetTransactionsByWalletIdQuery query);
}
