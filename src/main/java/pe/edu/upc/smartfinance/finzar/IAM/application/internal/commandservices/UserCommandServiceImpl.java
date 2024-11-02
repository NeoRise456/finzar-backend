package pe.edu.upc.smartfinance.finzar.IAM.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.aggregates.User;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.commands.CreateUserCommand;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.smartfinance.finzar.IAM.domain.services.UserCommandService;
import pe.edu.upc.smartfinance.finzar.IAM.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    
    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("User already exists");
        }
        var user = new User(command.username(), command.email(), command.password());
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        if (!userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("User does not exist");
        }
        var userToUpdate = userRepository.findByEmail(command.email()).get();
        if (userRepository.existsByEmailAndIdIsNot(command.email(), command.id())) {
            throw new IllegalArgumentException("Email is already being used");
        }
        var updatedUser = userRepository.save(userToUpdate.updateInformation(command.username(), command.email(), command.password()));
        return Optional.of(updatedUser);
    }

    @Override
    public void handle(DeleteUserCommand command) {
        if (!userRepository.existsById(command.userId())) {
            throw new IllegalArgumentException("User does not exist");
        }
        userRepository.deleteById(command.userId());
    }
}
