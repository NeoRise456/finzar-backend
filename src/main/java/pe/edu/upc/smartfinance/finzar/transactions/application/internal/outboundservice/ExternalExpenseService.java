package pe.edu.upc.smartfinance.finzar.transactions.application.internal.outboundservice;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.acl.ExpenseContextFacade;

import java.util.Optional;

@Service
public class ExternalExpenseService {

    public final ExpenseContextFacade expenseContextFacade;

    public ExternalExpenseService(@Lazy ExpenseContextFacade expenseContextFacade) {
        this.expenseContextFacade = expenseContextFacade;
    }

    public Boolean existsExpenseById(Long expenseId) {
        return expenseContextFacade.existsExpenseById(expenseId);
    }

    public void saveTransactionByExpenseId(Long expenseId, Long transactionId) {
        expenseContextFacade.saveTransactionByExpenseId(expenseId, transactionId);
    }

    public Optional<Expense> fetchExpenseById(Long expenseId) {
        return expenseContextFacade.fetchExpenseById(expenseId);
    }

}
