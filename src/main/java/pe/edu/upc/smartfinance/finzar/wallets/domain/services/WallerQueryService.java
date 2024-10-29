package pe.edu.upc.smartfinance.finzar.wallets.domain.services;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetAllWalletsQuery;

import java.util.List;

public interface WallerQueryService {
    List<Wallet> handle(GetAllWalletsQuery query);
}
