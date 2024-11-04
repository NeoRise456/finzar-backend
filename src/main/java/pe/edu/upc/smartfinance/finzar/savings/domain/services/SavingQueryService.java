package pe.edu.upc.smartfinance.finzar.savings.domain.services;


import pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetAllSavingsQuery;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetSavingByUserIdQuery;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetSavingByIdQuery;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetSavingByNameQuery;

import java.util.List;
import java.util.Optional;


public interface SavingQueryService {
    List<Saving> handle(GetAllSavingsQuery query);
    Optional<Saving> handle(GetSavingByIdQuery query);
    Optional<Saving> handle(GetSavingByNameQuery query);
    List<Saving> handle(GetSavingByUserIdQuery query);

}