package pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Expense;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findExpensesByWallet_IdAndCategory_Id(Long walletId, Long categoryId);
}
