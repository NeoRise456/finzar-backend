package pe.edu.upc.smartfinance.finzar.transactions.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Transaction;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.queries.GetTransactionByIdQuery;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.TransactionCommandService;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.TransactionQueryService;

import java.util.Optional;

@Service
public class TransactionsContextFacade {

    private final TransactionCommandService transactionsCommandService;
    private final TransactionQueryService transactionsQueryService;

    public TransactionsContextFacade(TransactionCommandService transactionsCommandService, TransactionQueryService transactionsQueryService) {
        this.transactionsCommandService = transactionsCommandService;
        this.transactionsQueryService = transactionsQueryService;
    }

    public Optional<Transaction> fetchTransactionById(Long transactionId) {
        return transactionsQueryService.handle(new GetTransactionByIdQuery(transactionId));
    }





}
