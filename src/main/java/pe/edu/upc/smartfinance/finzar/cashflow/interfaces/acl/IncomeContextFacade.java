package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Income;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.AddTransactionByIncomeIdCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetIncomeByIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.IncomeCommandService;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.IncomeQueryService;

import java.util.Optional;

@Service
public class IncomeContextFacade {

    private final IncomeCommandService incomeCommandService;
    private final IncomeQueryService incomeQueryService;

    public IncomeContextFacade(IncomeCommandService incomeCommandService, IncomeQueryService incomeQueryService) {
        this.incomeCommandService = incomeCommandService;
        this.incomeQueryService = incomeQueryService;
    }


    public Boolean existsIncomeById(Long incomeId) {
        return incomeQueryService.handle(new GetIncomeByIdQuery(incomeId)).isPresent();
    }

    public void saveTransactionByIncomeId(Long incomeId, Long transactionId) {
        var addTransactionByIncomeIdCommand = new AddTransactionByIncomeIdCommand(incomeId, transactionId);
        incomeCommandService.handle(addTransactionByIncomeIdCommand);
    }

    public Optional<Income> fetchIncomeById(Long incomeId) {
        return incomeQueryService.handle(new GetIncomeByIdQuery(incomeId));
    }
}
