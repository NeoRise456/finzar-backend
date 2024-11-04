package pe.edu.upc.smartfinance.finzar.transactions.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.queries.GetExpenseByIdQuery;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.queries.GetExpensesByWalletIdAndCategoryIdQuery;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.ExpenseQueryService;
import pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories.ExpenseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseQueryServiceImpl implements ExpenseQueryService {

    private final ExpenseRepository expenseRepository;

    public ExpenseQueryServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Optional<Expense> handle(GetExpenseByIdQuery query) {
        return this.expenseRepository.findById(query.expenseId());
    }

    @Override
    public List<Expense> handle(GetExpensesByWalletIdAndCategoryIdQuery query) {

        var expenses = this.expenseRepository.findExpensesByWallet_IdAndCategory_Id(
                query.walletId(), query.categoryId());

        return expenses;
    }
}
