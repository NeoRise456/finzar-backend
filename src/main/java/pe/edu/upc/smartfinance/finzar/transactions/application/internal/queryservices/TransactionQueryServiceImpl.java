package pe.edu.upc.smartfinance.finzar.transactions.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Transaction;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.queries.GetTransactionByIdQuery;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.queries.GetTransactionsByWalletIdQuery;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.TransactionQueryService;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.TransactionRepository;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.TransactionTypeRepository;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.WalletRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionQueryServiceImpl implements TransactionQueryService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionQueryServiceImpl(TransactionRepository transactionRepository,
                                         WalletRepository walletRepository,
                                         TransactionTypeRepository transactionTypeRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public Optional<Transaction> handle(GetTransactionByIdQuery query) {
        var transaction = this.transactionRepository.findById(query.transactionId());
        return transaction;
    }

    @Override
    public List<Transaction> handle(GetTransactionsByWalletIdQuery query) {

        var wallet = this.walletRepository.findById(query.walletId());

        if (wallet.isEmpty()) {
            throw new IllegalArgumentException("Wallet not found");
        }

        var transactions = this.transactionRepository.findTransactionsByWallet_Id(query.walletId());
        return transactions;

    }
}
