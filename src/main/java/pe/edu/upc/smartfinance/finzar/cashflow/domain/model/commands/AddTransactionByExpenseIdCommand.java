package pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands;

public record AddTransactionByExpenseIdCommand( Long expenseId, Long transactionId) {
}
