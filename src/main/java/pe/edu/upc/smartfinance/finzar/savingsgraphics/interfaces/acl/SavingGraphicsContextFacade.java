package pe.edu.upc.smartfinance.finzar.savingsgraphics.interfaces.acl;

import pe.edu.upc.smartfinance.finzar.savings.domain.services.SavingQueryService;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.services.SavingsGraphicsQueryService;

public class SavingGraphicsContextFacade {
    private final SavingsGraphicsQueryService savingsGraphicsQueryService ;

    public SavingGraphicsContextFacade(SavingsGraphicsQueryService savingsGraphicsQueryService) {
        this.savingsGraphicsQueryService = savingsGraphicsQueryService;
    }
}
