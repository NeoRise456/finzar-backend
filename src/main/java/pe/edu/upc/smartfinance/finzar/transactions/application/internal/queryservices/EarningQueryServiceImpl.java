package pe.edu.upc.smartfinance.finzar.transactions.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Earning;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.queries.GetEarningByIdQuery;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.queries.GetEarningsByWalletIdAndCategoryIdQuery;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.EarningQueryService;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.EarningRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EarningQueryServiceImpl implements EarningQueryService {

    private final EarningRepository earningRepository;

    public EarningQueryServiceImpl(EarningRepository earningRepository) {
        this.earningRepository = earningRepository;
    }

    @Override
    public Optional<Earning> handle(GetEarningByIdQuery query) {
        return this.earningRepository.findById(query.earningId());
    }

    @Override
    public List<Earning> handle(GetEarningsByWalletIdAndCategoryIdQuery query) {

        var earnigs = this.earningRepository.findEarningsByWallet_IdAndCategory_Id(
                query.walletId(),
                query.categoryId()
        );

        return earnigs;

    }
}
