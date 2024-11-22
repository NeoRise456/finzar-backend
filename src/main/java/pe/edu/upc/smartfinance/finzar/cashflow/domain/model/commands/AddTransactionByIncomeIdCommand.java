package pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands;

public record AddTransactionByIncomeIdCommand(Long incomeId, Long transactionId) {
}
