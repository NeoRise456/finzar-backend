package pe.edu.upc.smartfinance.finzar.transactions.application.internal.outboundservice;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.interfaces.acl.WalletContextFacade;

import java.util.Optional;

@Service
public class ExternalWalletService {

    private final WalletContextFacade walletContextFacade;

    public ExternalWalletService(WalletContextFacade walletContextFacade) {
        this.walletContextFacade = walletContextFacade;
    }

    public boolean existsWalletById(Long walletId) {
        return walletContextFacade.existsWalletById(walletId);
    }

    public Optional<Wallet> fetchWalletById(Long walletId) {
        return walletContextFacade.fetchWalletById(walletId);
    }

    public void addToBalanceById(Long walletId, Double amount) {
        walletContextFacade.addToBalanceById(walletId, amount);
    }

    public void subtractFromBalanceById(Long walletId, Double amount) {
        walletContextFacade.subtractFromBalanceById(walletId, amount);
    }




}
