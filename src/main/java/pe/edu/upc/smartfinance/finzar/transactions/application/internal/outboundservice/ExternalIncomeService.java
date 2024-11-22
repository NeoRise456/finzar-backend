package pe.edu.upc.smartfinance.finzar.transactions.application.internal.outboundservice;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Income;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.acl.IncomeContextFacade;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalIncomeService {

    public final IncomeContextFacade incomeContextFacade;

    public ExternalIncomeService(@Lazy IncomeContextFacade incomeContextFacade) {
        this.incomeContextFacade = incomeContextFacade;
    }

    public Boolean existsIncomeById(Long incomeId) {
        return incomeContextFacade.existsIncomeById(incomeId);
    }

    public void saveTransactionByIncomeId(Long incomeId, Long transactionId) {
        incomeContextFacade.saveTransactionByIncomeId(incomeId, transactionId);
    }


    public Optional<Income> fetchIncomeById(Long incomeId) {
        return incomeContextFacade.fetchIncomeById(incomeId);
    }
}
