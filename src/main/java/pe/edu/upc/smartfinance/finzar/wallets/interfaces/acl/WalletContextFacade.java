package pe.edu.upc.smartfinance.finzar.wallets.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.AddToBalanceByWalletIdCommand;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetWalletByIdQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.services.WalletCommandService;
import pe.edu.upc.smartfinance.finzar.wallets.domain.services.WalletQueryService;

import java.util.Optional;

@Service
public class WalletContextFacade {

    private final WalletCommandService walletCommandService;
    private final WalletQueryService walletQueryService;

    public WalletContextFacade(WalletCommandService walletCommandService, WalletQueryService walletQueryService) {
        this.walletCommandService = walletCommandService;
        this.walletQueryService = walletQueryService;
    }

    public boolean existsWalletById(long walletId) {

        var getWalletByIdQuery = new GetWalletByIdQuery(walletId);

        var optionalWallet = this.walletQueryService.handle(getWalletByIdQuery);

        return optionalWallet.isPresent();
    }



    public void addToBalanceById(Long walletId, Double amount) {
        var addToBalanceByWalletIdCommand = new AddToBalanceByWalletIdCommand(walletId, amount);
        this.walletCommandService.handle(addToBalanceByWalletIdCommand);
    }

    public void subtractFromBalanceById(Long wallet, Double amount) {
        var subtractFromBalanceByWalletIdCommand = new AddToBalanceByWalletIdCommand(wallet, -amount);
        this.walletCommandService.handle(subtractFromBalanceByWalletIdCommand);
    }

    public Optional<Wallet> fetchWalletById(Long walletId) {
        var getWalletByIdQuery = new GetWalletByIdQuery(walletId);
        return this.walletQueryService.handle(getWalletByIdQuery);
    }
}
