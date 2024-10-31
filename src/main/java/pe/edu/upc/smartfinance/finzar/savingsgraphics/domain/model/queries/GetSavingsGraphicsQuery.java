package pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.model.queries;

public record GetSavingsGraphicsQuery(Long id) {
    public GetSavingsGraphicsQuery {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
    }
}
