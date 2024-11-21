package pe.edu.upc.smartfinance.finzar.iam.domain.services;

import pe.edu.upc.smartfinance.finzar.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
