package pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands;

public record CreateIncomeCommand(
        Long walletId,
        Double amount,
        Long categoryId,
        String periodRecurrence
) {
}
