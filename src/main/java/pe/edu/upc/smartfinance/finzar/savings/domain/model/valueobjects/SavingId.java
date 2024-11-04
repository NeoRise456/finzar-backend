package pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects;

public record SavingId(Long value) {
    public SavingId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Saving ID must be positive and non-null");
        }
    }
}