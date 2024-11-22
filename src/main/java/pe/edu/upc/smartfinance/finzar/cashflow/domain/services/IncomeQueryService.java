package pe.edu.upc.smartfinance.finzar.cashflow.domain.services;

import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Income;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetIncomeByIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetIncomesByWalletIdAndCategoryIdQuery;

import java.util.List;
import java.util.Optional;

public interface IncomeQueryService {
    Optional<Income> handle(GetIncomeByIdQuery query);
    List<Income> handle(GetIncomesByWalletIdAndCategoryIdQuery query);

}
