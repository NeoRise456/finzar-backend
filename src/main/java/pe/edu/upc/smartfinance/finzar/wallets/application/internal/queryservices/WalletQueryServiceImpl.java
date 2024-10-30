package pe.edu.upc.smartfinance.finzar.wallets.application.internal.queryservices;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetAllWalletsQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.services.WallerQueryService;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.WalletRepository;

import java.util.List;

public class WalletQueryServiceImpl implements WallerQueryService {

    private final WalletRepository walletRepository;

    public WalletQueryServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public List<Wallet> handle(GetAllWalletsQuery query) {
        return walletRepository.findAll();
    }
}
