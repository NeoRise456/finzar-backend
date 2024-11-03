package pe.edu.upc.smartfinance.finzar.savings.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetAllSavingsQuery;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetSavingByIdQuery;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetSavingByNameQuery;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetSavingByUserIdQuery;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects.UserId;
import pe.edu.upc.smartfinance.finzar.savings.domain.services.SavingQueryService;
import pe.edu.upc.smartfinance.finzar.savings.infrastructure.persistence.jpa.repositories.SavingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SavingQueryServiceImpl implements SavingQueryService {

    private final SavingRepository savingRepository;

    public SavingQueryServiceImpl(SavingRepository savingRepository) {
        this.savingRepository = savingRepository;
    }


    @Override
    public List<Saving> handle(GetAllSavingsQuery query) {
        return this.savingRepository.findAll();
    }

    @Override
    public Optional<Saving> handle(GetSavingByIdQuery query) {
        return this.savingRepository.findById(query.savingId());
    }

    @Override
    public Optional<Saving> handle(GetSavingByNameQuery query) {
        return this.savingRepository.findByName(query.name());
    }

    @Override
    public List<Saving> handle(GetSavingByUserIdQuery query) {
        var userId = new UserId(query.userId()); // Crear el objeto UserId a partir del Long
        return this.savingRepository.findSavingsByUserId(userId);
    }

}