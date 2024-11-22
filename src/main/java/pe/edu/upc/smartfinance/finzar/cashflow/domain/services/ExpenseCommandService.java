package pe.edu.upc.smartfinance.finzar.cashflow.domain.services;

import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.CreateExpenseCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.DeleteExpenseCommand;


import java.util.Optional;

public interface ExpenseCommandService {
    Long handle(CreateExpenseCommand command);
    Boolean handle(DeleteExpenseCommand command);
}
