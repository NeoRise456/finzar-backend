package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources.WalletResource;

public class WalletResourceFromEntityAssembler {
    public static WalletResource toResourceFromEntity(Wallet entity) {
        return new WalletResource(entity.getId(), entity.getName(), entity.getBalance(),
                SimplifiedUserResourceFromEntityAssembler.toResourceFromEntity(entity.getUser()));
    }
}
