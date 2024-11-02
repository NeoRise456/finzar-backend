package pe.edu.upc.smartfinance.finzar.IAM.domain.services;

import pe.edu.upc.smartfinance.finzar.IAM.domain.model.aggregates.User;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> handle(GetUserByIdQuery query);
    List<User> handle(GetAllUsersQuery query);
}
