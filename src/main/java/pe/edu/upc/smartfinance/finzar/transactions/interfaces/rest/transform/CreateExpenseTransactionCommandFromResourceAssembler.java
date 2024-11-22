package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.CreateExpenseTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.CreateCashflowTransactionResource;

public class CreateExpenseTransactionCommandFromResourceAssembler {
    public static CreateExpenseTransactionCommand toCommandFromResource(CreateCashflowTransactionResource resource, Long expenseId){
        return new CreateExpenseTransactionCommand(
                resource.sourceWalletId(),
                resource.note(),
                resource.amount(),
                resource.transactionDate(),
                expenseId
        );
    }
}
