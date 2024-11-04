package pe.edu.upc.smartfinance.finzar.savings.domain.services;

import pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.CreateSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.DeleteSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.UpdateSavingCommand;

import java.util.Optional;

public interface SavingCommandService {
    Long handle(CreateSavingCommand command);
    Optional<Saving> handle(UpdateSavingCommand command);
    void handle(DeleteSavingCommand command);
}