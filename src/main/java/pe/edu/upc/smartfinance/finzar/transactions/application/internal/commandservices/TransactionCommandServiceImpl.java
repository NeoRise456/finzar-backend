package pe.edu.upc.smartfinance.finzar.transactions.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Transaction;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.DeleteTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.UpdateTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities.TransactionType;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.TransactionCommandService;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.TransactionRepository;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.TransactionTypeRepository;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.WalletRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionCommandServiceImpl implements TransactionCommandService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionCommandServiceImpl(TransactionRepository transactionRepository,
                                         WalletRepository walletRepository,
                                         TransactionTypeRepository transactionTypeRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public Long handle(CreateTransactionCommand command) {

        var wallet = this.walletRepository.findById(command.walletId());

        if (!wallet.isPresent()) {
            throw new IllegalArgumentException("Wallet not found");
        }

        var transactionType = this.transactionTypeRepository.findById(command.transactionTypeId());


        if (!transactionType.isPresent()) {
            throw new IllegalArgumentException("Transaction type not found");
        }

        var transaction = new Transaction(
                wallet.get(),
                transactionType.get(),
                command.note(),
                command.amount(),
                LocalDateTime.now()
        );

        this.transactionRepository.save(transaction);

        return transaction.getId();
    }

    @Override
    public Boolean handle(DeleteTransactionCommand command) {

        if (!this.transactionRepository.existsById(command.transactionId())) {
            throw new IllegalArgumentException("Transaction not found");
        }
        this.transactionRepository.deleteById(command.transactionId());
        return true;
    }


    //TODO: Implement update handle for transaction
    @Override
    public Optional<Transaction> handle(UpdateTransactionCommand command) {
        return Optional.empty();
    }
}
