package pe.edu.upc.smartfinance.finzar.IAM.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.aggregates.User;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.smartfinance.finzar.IAM.domain.services.UserQueryService;
import pe.edu.upc.smartfinance.finzar.IAM.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }
}
