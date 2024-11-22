package pe.edu.upc.smartfinance.finzar.cashflow.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Income;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetIncomeByIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetIncomesByWalletIdAndCategoryIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.IncomeQueryService;
import pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories.IncomeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EarningQueryServiceImpl implements IncomeQueryService {

    private final IncomeRepository earningRepository;

    public EarningQueryServiceImpl(IncomeRepository earningRepository) {
        this.earningRepository = earningRepository;
    }

    @Override
    public Optional<Income> handle(GetIncomeByIdQuery query) {
        return this.earningRepository.findById(query.earningId());
    }

    @Override
    public List<Income> handle(GetIncomesByWalletIdAndCategoryIdQuery query) {

        var earnigs = this.earningRepository.findEarningsByWallet_IdAndCategory_Id(
                query.walletId(),
                query.categoryId()
        );

        return earnigs;

    }
}
