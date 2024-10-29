package pe.edu.upc.smartfinance.finzar.wallets.domain.services;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.CreateWalletCommand;

public interface WalletCommandService {
    Long handle(CreateWalletCommand command);
}
