package pe.edu.upc.smartfinance.finzar.transactions.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateExpenseCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.DeleteExpenseCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.UpdateExpenseCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities.PeriodRecurrence;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.ExpenseCommandService;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.CategoryRepository;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.PeriodRecurrenceRepository;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.WalletRepository;

import java.util.Optional;

@Service
public class ExpenseCommandServiceImpl implements ExpenseCommandService {

    private final ExpenseRepository expenseRepository;
    private final WalletRepository walletRepository;
    private final CategoryRepository categoryRepository;
    private final PeriodRecurrenceRepository periodRecurrenceRepository;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository , WalletRepository walletRepository, CategoryRepository categoryRepository, PeriodRecurrenceRepository periodRecurrenceRepository) {
        this.expenseRepository = expenseRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
        this.periodRecurrenceRepository = periodRecurrenceRepository;
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

        var periodRecurrence = this.periodRecurrenceRepository.findById(command.periodRecurrenceId());

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

    //TODO: Implement update handle for Expense
    @Override
    public Optional<Expense> handle(UpdateExpenseCommand command) {
        return Optional.empty();
    }
}
