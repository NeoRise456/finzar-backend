package pe.edu.upc.smartfinance.finzar.wallets.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.*;
import pe.edu.upc.smartfinance.finzar.wallets.domain.services.WalletCommandService;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.WalletRepository;

import java.util.Optional;

@Service
public class WalletCommandServiceImpl implements WalletCommandService {

    private final WalletRepository walletRepository;

    //TODO: add user external service
    private final UserRepository userRepository;

    public WalletCommandServiceImpl(WalletRepository walletRepository , UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;

    }

    @Override
    public Long handle(CreateWalletCommand command) {
        var optionalUser = this.userRepository.findById(command.userId());
        if (optionalUser.isEmpty()){
            throw new IllegalArgumentException("User not found");
        }
        if (this.walletRepository.existsByNameAndUserId(command.name(), command.userId())){
            throw new IllegalArgumentException("Wallet already exists");
        }

        var user = optionalUser.get();

        var wallet = new Wallet(
                user,
                command.name(),
                command.balance()
        );

        this.walletRepository.save(wallet);
        return wallet.getId();
    }

    @Override
    public Optional<Wallet> handle(UpdateWalletCommand command) {
        if(!this.walletRepository.existsById(command.walletId())){
            throw new IllegalArgumentException("Wallet not found");
        }

        var walletToUpdate = this.walletRepository.findById(command.walletId()).get();
        walletToUpdate.updateInformation(command.name(), command.balance());

        try {
            var updatedWallet = this.walletRepository.save(walletToUpdate);
            return Optional.of(updatedWallet);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating wallet: " + e.getMessage());
        }
    }

    @Override
    public Boolean handle(DeleteWalletCommand command) {
        if (!this.walletRepository.existsById(command.walletId())){
            throw new IllegalArgumentException("Wallet not found");
        }
        this.walletRepository.deleteById(command.walletId());
        return !this.walletRepository.existsById(command.walletId());
    }

    @Override
    public void handle(AddToBalanceByWalletIdCommand command) {
        if (!this.walletRepository.existsById(command.walletId())){
            throw new IllegalArgumentException("Wallet not found");
        }

        var wallet = this.walletRepository.findById(command.walletId()).get();
        wallet.addToBalance(command.amount());

        try {
            this.walletRepository.save(wallet);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while adding to balance: " + e.getMessage());
        }
    }

    @Override
    public void handle(SubtractToBalanceByWalletIdCommand command) {

        if (!this.walletRepository.existsById(command.walletId())){
            throw new IllegalArgumentException("Wallet not found");
        }

        var wallet = this.walletRepository.findById(command.walletId()).get();

        if (wallet.getBalance() < command.amount()){
            throw new IllegalArgumentException("Insufficient balance");
        }

        wallet.subtractFromBalance(command.amount());

        try {
            this.walletRepository.save(wallet);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while subtracting to balance: " + e.getMessage());
        }
    }
}
