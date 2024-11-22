package pe.edu.upc.smartfinance.finzar.transactions.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.transactions.application.internal.outboundservice.ExternalIncomeService;
import pe.edu.upc.smartfinance.finzar.transactions.application.internal.outboundservice.ExternalWalletService;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Transaction;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateExpenseTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateIncomeTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateTransactionToWalletCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.DeleteTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.valueobjects.TransactionTypes;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.TransactionCommandService;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.TransactionRepository;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.TransactionTypeRepository;

import java.util.Optional;

@Service
public class TransactionCommandServiceImpl implements TransactionCommandService {

    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;

    private final ExternalWalletService externalWalletService;
    private final ExternalIncomeService externalIncomeService;

    public TransactionCommandServiceImpl(TransactionRepository transactionRepository,
                                         ExternalWalletService externalWalletService,
                                         TransactionTypeRepository transactionTypeRepository,
                                         ExternalIncomeService externalIncomeService) {
        this.transactionRepository = transactionRepository;
        this.externalWalletService = externalWalletService;
        this.transactionTypeRepository = transactionTypeRepository;
        this.externalIncomeService = externalIncomeService;
    }


    @Override
    public Long handle(CreateTransactionToWalletCommand command) {

        var sourceWallet = this.externalWalletService.fetchWalletById(command.sourceWalletId());

        if (sourceWallet.isEmpty()) {
            throw new IllegalArgumentException("Source Wallet not found");
        }

        var destinationWallet = this.externalWalletService.fetchWalletById(command.destinationWalletId());

        if (destinationWallet.isEmpty()) {
            throw new IllegalArgumentException("Destination Wallet not found");
        }

        var sourceTransactionType = this.transactionTypeRepository.findByName(
                TransactionTypes.OUT_TRANSFER
        ).orElseThrow(() -> new IllegalArgumentException("Transaction Type not found"));

        var destinationTransactionType = this.transactionTypeRepository.findByName(
                TransactionTypes.IN_TRANSFER
        ).orElseThrow(() -> new IllegalArgumentException("Transaction Type not found"));

        this.externalWalletService.subtractFromBalanceById(sourceWallet.get().getId(), command.amount());
        this.externalWalletService.addToBalanceById(destinationWallet.get().getId(), command.amount());

        var sourceTransaction = new Transaction(
                sourceWallet.get(),
                sourceTransactionType,
                command.note(),
                command.amount(),
                command.transactionDate()
        );

        var destinationTransaction = new Transaction(
                destinationWallet.get(),
                destinationTransactionType,
                command.note(),
                command.amount(),
                command.transactionDate()
        );

        try {
            this.transactionRepository.save(sourceTransaction);
            this.transactionRepository.save(destinationTransaction);
            return sourceTransaction.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving transaction");
        }


    }

    @Override
    public Boolean handle(DeleteTransactionCommand command) {

        if (!transactionRepository.existsById(command.transactionId())) {
            throw new IllegalArgumentException("Transaction not found");
        }

        try {
            transactionRepository.deleteById(command.transactionId());
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting transaction");
        }
    }

    @Override
    public Long handle(CreateIncomeTransactionCommand command) {

        var sourceWallet = this.externalWalletService.fetchWalletById(command.sourceWalletId());

        if (sourceWallet.isEmpty()) {
            throw new IllegalArgumentException("Source Wallet not found");
        }

        if(!externalIncomeService.existsIncomeById(command.incomeId())){
            throw new IllegalArgumentException("Income not found");
        }

        var sourceTransactionType = this.transactionTypeRepository.findByName(
                TransactionTypes.INCOME
        ).orElseThrow(() -> new IllegalArgumentException("Transaction Type not found"));

        this.externalWalletService.addToBalanceById(sourceWallet.get().getId(), command.amount());

        var sourceTransaction = new Transaction(
                sourceWallet.get(),
                sourceTransactionType,
                command.note(),
                command.amount(),
                command.transactionDate()
        );

        var income = externalIncomeService.fetchIncomeById(command.incomeId()).get();

        sourceTransaction.getIncomes().add(income);

        try {
            this.transactionRepository.save(sourceTransaction);

        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving transaction");
        }

        externalIncomeService.saveTransactionByIncomeId(command.incomeId(), sourceTransaction.getId());
        return sourceTransaction.getId();
    }

    @Override
    public Long handle(CreateExpenseTransactionCommand command) {

        var sourceWallet = this.externalWalletService.fetchWalletById(command.sourceWalletId());

        if (sourceWallet.isEmpty()) {
            throw new IllegalArgumentException("Source Wallet not found");
        }




        return 0L;
    }
}
