package pe.edu.upc.smartfinance.finzar.wallets.domain.services;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.*;

import java.util.Optional;

public interface WalletCommandService {
    Long handle(CreateWalletCommand command);
    Optional<Wallet> handle(UpdateWalletCommand command);
    Boolean handle(DeleteWalletCommand command);
    void handle(AddToBalanceByWalletIdCommand command);
    void handle(SubtractToBalanceByWalletIdCommand command);
}
