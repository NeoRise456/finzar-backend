package pe.edu.upc.smartfinance.finzar.cashflow.application.internal.commandservices;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.cashflow.application.internal.outboundservices.ExternalTransactionService;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.AddTransactionByExpenseIdCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.CreateExpenseCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.DeleteExpenseCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.valueobjects.PeriodRecurrences;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.ExpenseCommandService;
import pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories.CategoryRepository;
import pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories.PeriodRecurrenceRepository;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.WalletRepository;

import java.util.Optional;

@Service
public class ExpenseCommandServiceImpl implements ExpenseCommandService {

    private final ExpenseRepository expenseRepository;
    private final WalletRepository walletRepository;
    private final CategoryRepository categoryRepository;
    private final PeriodRecurrenceRepository periodRecurrenceRepository;
    private final ExternalTransactionService externalTransactionService;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository ,
                                     WalletRepository walletRepository,
                                     CategoryRepository categoryRepository,
                                     PeriodRecurrenceRepository periodRecurrenceRepository,
                                     @Lazy ExternalTransactionService externalTransactionService) {
        this.expenseRepository = expenseRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
        this.periodRecurrenceRepository = periodRecurrenceRepository;
        this.externalTransactionService = externalTransactionService;
    }

    @Override
    public Long handle(CreateExpenseCommand command) {

        var wallet = this.walletRepository.findById(command.walletId());

        if(!wallet.isPresent()){
            throw new IllegalArgumentException("Wallet not found");
        }

        var category = this.categoryRepository.findById(command.categoryId());


        if(!category.isPresent()){
            throw new IllegalArgumentException("Category not found");
        }

        var periodRecurrence = this.periodRecurrenceRepository.findByName(
                PeriodRecurrences.valueOf(command.periodRecurrence())
        );

        if(!periodRecurrence.isPresent()){
            throw new IllegalArgumentException("Period Recurrence not found");
        }

        var expense = new Expense(
                command.amount(),
                wallet.get(),
                category.get(),
                periodRecurrence.get()
        );

        this.expenseRepository.save(expense);

        return expense.getId();
    }

    @Override
    public Boolean handle(DeleteExpenseCommand command) {
        if(!this.expenseRepository.existsById(command.expenseId())){
            throw new IllegalArgumentException("Expense not found");
        }
        this.expenseRepository.deleteById(command.expenseId());
        return !this.expenseRepository.existsById(command.expenseId());
    }

    @Override
    public void handle(AddTransactionByExpenseIdCommand command) {

        if(!expenseRepository.existsById(command.expenseId())){
            throw new IllegalArgumentException("Expense not found");
        }

        var expense = expenseRepository.findById(command.expenseId()).get();

        var transaction = externalTransactionService.fetchTransactionById(command.transactionId());

        expense.getTransactions().add(transaction.get());


        this.expenseRepository.save(expense);


    }

}
