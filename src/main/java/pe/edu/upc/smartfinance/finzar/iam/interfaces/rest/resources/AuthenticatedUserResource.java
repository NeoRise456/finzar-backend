package pe.edu.upc.smartfinance.finzar.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
