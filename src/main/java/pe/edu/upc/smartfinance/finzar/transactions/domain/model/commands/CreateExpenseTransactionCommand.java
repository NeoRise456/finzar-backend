package pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands;

import java.util.Date;

public record CreateExpenseTransactionCommand(
        Long sourceWalletId,
        String note,
        Double amount,
        Date transactionDate,
        Long expenseId
) {
}
