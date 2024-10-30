package pe.edu.upc.smartfinance.finzar.wallets.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetAllWalletsQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetWalletByIdQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.services.WalletQueryService;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.WalletRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WalletQueryServiceImpl implements WalletQueryService {

    private final WalletRepository walletRepository;

    public WalletQueryServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public List<Wallet> handle(GetAllWalletsQuery query) {
        return walletRepository.findAll();
    }

    @Override
    public Optional<Wallet> handle(GetWalletByIdQuery query) {
        return this.walletRepository.findById(query.walletId());
    }
}
