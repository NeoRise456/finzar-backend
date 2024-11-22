package pe.edu.upc.smartfinance.finzar.cashflow.application.internal.outboundservices;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Transaction;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.acl.TransactionsContextFacade;

import java.util.Optional;

@Service
public class ExternalTransactionService {

    private final TransactionsContextFacade transactionsContextFacade;

    public ExternalTransactionService(@Lazy TransactionsContextFacade transactionsContextFacade) {
        this.transactionsContextFacade = transactionsContextFacade;
    }

    public Optional<Transaction> fetchTransactionById(Long transactionId) {
        return transactionsContextFacade.fetchTransactionById(transactionId);
    }



}
