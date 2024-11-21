package pe.edu.upc.smartfinance.finzar.wallets.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.smartfinance.finzar.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetAllWalletsQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetWalletByIdQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetWalletsByUserIdQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.services.WalletQueryService;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.WalletRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WalletQueryServiceImpl implements WalletQueryService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletQueryServiceImpl(WalletRepository walletRepository , UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Wallet> handle(GetWalletsByUserIdQuery query) {
        if (!this.userRepository.existsById(query.userId())){
            throw new IllegalArgumentException("User not found");
        }

        return this.walletRepository.findWalletsByUser_Id(query.userId());
    }

    @Override
    public List<Wallet> handle(GetAllWalletsQuery query) {
        return this.walletRepository.findAll();
    }

    @Override
    public Optional<Wallet> handle(GetWalletByIdQuery query) {
        return this.walletRepository.findById(query.walletId());
    }
}
