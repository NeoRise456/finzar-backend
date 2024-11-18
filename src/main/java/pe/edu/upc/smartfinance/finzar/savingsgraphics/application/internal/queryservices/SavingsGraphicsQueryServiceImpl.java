package pe.edu.upc.smartfinance.finzar.savingsgraphics.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.model.aggregates.SavingsGraphics;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.services.SavingsGraphicsQueryService;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.infrastructure.persistence.jpa.repositories.SavingsGraphicsRepository;

import java.util.List;

@Service
public class SavingsGraphicsQueryServiceImpl implements SavingsGraphicsQueryService {

    private final SavingsGraphicsRepository savingsGraphicsRepository;

    public SavingsGraphicsQueryServiceImpl(SavingsGraphicsRepository savingsGraphicsRepository) {
        this.savingsGraphicsRepository = savingsGraphicsRepository;
    }

    @Override
    public List<SavingsGraphics> getAllSavingsGraphics() {
        return savingsGraphicsRepository.findAll();
    }
}