package pe.edu.upc.smartfinance.finzar.cashflow.domain.services;


import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetExpenseByIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetExpensesByWalletIdAndCategoryIdQuery;

import java.util.List;
import java.util.Optional;

public interface ExpenseQueryService {
    Optional<Expense> handle(GetExpenseByIdQuery query);
    List<Expense> handle(GetExpensesByWalletIdAndCategoryIdQuery query);
}
