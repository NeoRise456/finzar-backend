package pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.model.valueobjects;

public record CurrentAmount(Double value) {
    public CurrentAmount {
        if (value == null || value < 0) {
            throw new IllegalArgumentException("Current amount cannot be negative or null");
        }
    }
}
