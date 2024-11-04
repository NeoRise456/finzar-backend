package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.SimplifiedWalletResource;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;

public class SimplifiedWalletResourceFromEntityAssembler {
    public static SimplifiedWalletResource toResourceFromEntity(Wallet entity) {
        return new SimplifiedWalletResource(
                entity.getId(),
                entity.getName(),
                entity.getBalance()
        );
    }
}
