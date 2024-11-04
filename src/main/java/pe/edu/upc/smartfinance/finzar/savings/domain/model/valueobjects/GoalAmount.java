package pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects;

public record GoalAmount(Double value) {
    public GoalAmount {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Goal amount must be positive and non-null");
        }
    }
}