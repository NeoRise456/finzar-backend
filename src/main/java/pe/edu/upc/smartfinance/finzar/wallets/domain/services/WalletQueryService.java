package pe.edu.upc.smartfinance.finzar.wallets.domain.services;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetAllWalletsQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetWalletByIdQuery;


import java.util.List;
import java.util.Optional;

public interface WalletQueryService {
    List<Wallet> handle(GetAllWalletsQuery query);
    Optional<Wallet> handle(GetWalletByIdQuery query);
}
