package pe.edu.upc.smartfinance.finzar.iam.domain.model.commands;

import pe.edu.upc.smartfinance.finzar.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}
