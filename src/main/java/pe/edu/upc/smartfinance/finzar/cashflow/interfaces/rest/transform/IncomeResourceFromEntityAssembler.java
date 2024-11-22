package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Income;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources.IncomeResource;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform.SimplifiedWalletResourceFromEntityAssembler;

public class IncomeResourceFromEntityAssembler {
    public static IncomeResource toResourceFromEntity(Income entity) {
        return new IncomeResource(
                entity.getId(),
                SimplifiedWalletResourceFromEntityAssembler.toResourceFromEntity(entity.getWallet()),
                entity.getCategory().getName(),
                entity.getPeriodRecurrence().getName().toString(),
                entity.getAmount()
        );
    }
}
