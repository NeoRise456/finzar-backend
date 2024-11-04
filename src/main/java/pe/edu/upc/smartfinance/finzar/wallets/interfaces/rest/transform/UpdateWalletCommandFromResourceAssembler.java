package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.UpdateWalletCommand;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources.SimplifiedWalletResource;

public class UpdateWalletCommandFromResourceAssembler {
    public static UpdateWalletCommand toCommandFromResource(Long walletId, SimplifiedWalletResource resource) {
        return new UpdateWalletCommand(walletId, resource.name(), resource.balance());
    }
}
