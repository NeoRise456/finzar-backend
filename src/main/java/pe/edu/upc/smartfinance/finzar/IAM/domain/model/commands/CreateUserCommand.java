package pe.edu.upc.smartfinance.finzar.IAM.domain.model.commands;

public record CreateUserCommand(String username, String email, String password) {
}
