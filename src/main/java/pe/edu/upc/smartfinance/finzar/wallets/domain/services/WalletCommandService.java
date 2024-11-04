package pe.edu.upc.smartfinance.finzar.wallets.domain.services;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.CreateWalletCommand;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.DeleteWalletCommand;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.UpdateWalletCommand;

import java.util.Optional;

public interface WalletCommandService {
    Long handle(CreateWalletCommand command);
    Optional<Wallet> handle(UpdateWalletCommand command);
    Boolean handle(DeleteWalletCommand command);
}
