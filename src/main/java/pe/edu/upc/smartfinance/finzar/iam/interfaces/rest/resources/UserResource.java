package pe.edu.upc.smartfinance.finzar.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String username, List<String> roles) {
}
