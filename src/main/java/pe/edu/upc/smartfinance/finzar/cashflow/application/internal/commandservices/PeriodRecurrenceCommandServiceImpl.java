package pe.edu.upc.smartfinance.finzar.cashflow.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.SeedPeriodRecurrencesCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.entities.PeriodRecurrence;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.valueobjects.PeriodRecurrences;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.PeriodRecurrenceCommandService;
import pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories.PeriodRecurrenceRepository;

import java.util.Arrays;

@Service
public class PeriodRecurrenceCommandServiceImpl implements PeriodRecurrenceCommandService {

    private final PeriodRecurrenceRepository periodRecurrenceRepository;

    public PeriodRecurrenceCommandServiceImpl(PeriodRecurrenceRepository periodRecurrenceRepository) {
        this.periodRecurrenceRepository = periodRecurrenceRepository;
    }

    @Override
    public void handle(SeedPeriodRecurrencesCommand command) {
        Arrays.stream(PeriodRecurrences.values())
                .forEach(periodRecurrence -> {
                    if (!periodRecurrenceRepository.existsByName(periodRecurrence)) {
                        periodRecurrenceRepository.save(
                                new PeriodRecurrence(
                                        PeriodRecurrences.valueOf(periodRecurrence.name())
                                )
                        );
                    }
                });
    }
}
