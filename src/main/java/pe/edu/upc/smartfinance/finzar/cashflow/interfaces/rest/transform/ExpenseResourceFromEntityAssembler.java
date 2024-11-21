package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform.SimplifiedWalletResourceFromEntityAssembler;

public class ExpenseResourceFromEntityAssembler {
    public static ExpenseResource toResourceFromEntity(Expense entity) {
        return new ExpenseResource(
                entity.getId(),
                SimplifiedWalletResourceFromEntityAssembler.toResourceFromEntity(entity.getWallet()),
                entity.getCategory().getName(),
                entity.getPeriodRecurrence().getName()
        );
    }
}
