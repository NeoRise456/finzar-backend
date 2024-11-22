package pe.edu.upc.smartfinance.finzar.cashflow.domain.model.valueobjects;

public enum PeriodRecurrences {
    DAILY(1),
    WEEKLY(2),
    BIWEEKLY(3),
    MONTHLY(4),
    BIMONTHLY(5),
    QUARTERLY(6),
    SEMIANNUALLY(7),
    ANNUALLY(8);

    private final int value;

    PeriodRecurrences(int value) {
        this.value = value;
    }
}
