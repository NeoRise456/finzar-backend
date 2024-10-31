package pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects;

public record UserId(Long value) {
    public UserId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("User ID must be positive and non-null");
        }
    }
}