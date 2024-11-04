package pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands;

public record UpdateTransactionCommand(
        Long transactionId,
        String note,
        Double amount
) {
}
