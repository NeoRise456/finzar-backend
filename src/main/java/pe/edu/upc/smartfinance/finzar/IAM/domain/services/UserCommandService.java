package pe.edu.upc.smartfinance.finzar.IAM.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.aggregates.User;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.commands.CreateUserCommand;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.commands.UpdateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Long handle(CreateUserCommand command);

    Optional<User> handle(UpdateUserCommand command);

    void handle(DeleteUserCommand command);
}
