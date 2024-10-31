package pe.edu.upc.smartfinance.finzar.savingsgraphics.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.model.aggregates.SavingsGraphics;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.services.SavingsGraphicsCommandService;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.infrastructure.persistence.jpa.repositories.SavingsGraphicsRepository;

import java.util.List;

@Service
public class SavingsGraphicsCommandServiceImpl implements SavingsGraphicsCommandService {

    private final SavingsGraphicsRepository savingsGraphicsRepository;

    public SavingsGraphicsCommandServiceImpl(SavingsGraphicsRepository savingsGraphicsRepository) {
        this.savingsGraphicsRepository = savingsGraphicsRepository;
    }
    @Override
    public List<SavingsGraphics> getAllSavingsGraphics() {
        return savingsGraphicsRepository.findAll();
    }
}
