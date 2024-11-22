package pe.edu.upc.smartfinance.finzar.transactions.domain.model.valueobjects;

public enum TransactionTypes {
    INCOME(1),
    EXPENSE (2),
    OUT_TRANSFER (3),
    IN_TRANSFER (4);

    private final int value;

    TransactionTypes(int value) {
        this.value = value;
    }
}
