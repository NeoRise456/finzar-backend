package pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.transform;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.CreateWalletCommand;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.rest.resources.CreateWalletResource;

public class CreateWalletCommandFromResourceAssembler {
    public static CreateWalletCommand toCommandFromResource(CreateWalletResource resource) {
        return new CreateWalletCommand(resource.userId(), resource.name(), resource.initialBalance());
    }
}
