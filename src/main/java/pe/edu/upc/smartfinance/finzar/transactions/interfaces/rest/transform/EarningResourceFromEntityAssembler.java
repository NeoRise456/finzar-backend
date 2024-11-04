package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Earning;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.EarningResource;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.ExpenseResource;

public class EarningResourceFromEntityAssembler {
    public static EarningResource toResourceFromEntity(Earning entity) {
        return new EarningResource(
                entity.getId(),
                SimplifiedWalletResourceFromEntityAssembler.toResourceFromEntity(entity.getWallet()),
                entity.getCategory().getName(),
                entity.getPeriodRecurrence().getName()
        );
    }
}
