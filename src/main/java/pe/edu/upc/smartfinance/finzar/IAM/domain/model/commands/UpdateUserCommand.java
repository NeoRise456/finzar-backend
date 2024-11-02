package pe.edu.upc.smartfinance.finzar.IAM.domain.model.commands;

public record UpdateUserCommand(Long id, String username, String email, String password) {
}
