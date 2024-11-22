package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Earning;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources.EarningResource;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform.SimplifiedWalletResourceFromEntityAssembler;

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
