package pe.edu.upc.smartfinance.finzar.cashflow.domain.services;

import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Earning;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.CreateEarningCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.DeleteEarningCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.UpdateEarningCommand;

import java.util.Optional;

public interface EarningCommandService {
    Long handle(CreateEarningCommand command);
    Boolean handle(DeleteEarningCommand command);
    Optional<Earning> handle(UpdateEarningCommand command);

}
