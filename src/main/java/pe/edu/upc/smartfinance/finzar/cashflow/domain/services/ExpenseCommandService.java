package pe.edu.upc.smartfinance.finzar.cashflow.domain.services;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.CreateExpenseCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.DeleteExpenseCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.UpdateExpenseCommand;

import java.util.Optional;

public interface ExpenseCommandService {
    Long handle(CreateExpenseCommand command);
    Boolean handle(DeleteExpenseCommand command);
    Optional<Expense> handle(UpdateExpenseCommand command);
}
