package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.AddTransactionByExpenseIdCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetExpenseByIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.ExpenseCommandService;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.ExpenseQueryService;

import java.util.Optional;

@Service
public class ExpenseContextFacade {

    private final ExpenseCommandService expenseCommandService;
    private final ExpenseQueryService expenseQueryService;

    public ExpenseContextFacade(ExpenseCommandService expenseCommandService, ExpenseQueryService expenseQueryService) {
        this.expenseCommandService = expenseCommandService;
        this.expenseQueryService = expenseQueryService;
    }

    public Boolean existsExpenseById(Long expenseId) {
        return expenseQueryService.handle(new GetExpenseByIdQuery(expenseId)).isPresent();
    }

    public void saveTransactionByExpenseId(Long expenseId, Long transactionId) {
        var addTransactionByExpenseIdCommand = new AddTransactionByExpenseIdCommand(expenseId, transactionId);
        expenseCommandService.handle(addTransactionByExpenseIdCommand);
    }

    public Optional<Expense> fetchExpenseById(Long expenseId) {
        return expenseQueryService.handle(new GetExpenseByIdQuery(expenseId));
    }


}
