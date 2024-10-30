package pe.edu.upc.smartfinance.finzar.wallets.application.internal.commandservices;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.CreateWalletCommand;
import pe.edu.upc.smartfinance.finzar.wallets.domain.services.WalletCommandService;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.WalletRepository;

public class WalletCommandServiceImpl implements WalletCommandService {

    private final WalletRepository walletRepository;

    public WalletCommandServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Long handle(CreateWalletCommand command) {
        var name = command.name();
        var userId = command.userId();
        if (this.walletRepository.existsByNameAndUserIdIs(name, userId)) {
            throw new IllegalArgumentException("Wallet "+ name +" already exists for you");
        }

        var wallet = new Wallet(command);

        try{
            this.walletRepository.save(wallet);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error while saving wallet: " + e.getMessage());
        }

        return wallet.getId();
    }
}
