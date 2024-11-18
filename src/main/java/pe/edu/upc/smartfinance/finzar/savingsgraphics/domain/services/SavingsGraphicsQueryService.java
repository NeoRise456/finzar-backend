package pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.services;

import pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.model.aggregates.SavingsGraphics;
import java.util.List;

public interface SavingsGraphicsQueryService {
    List<SavingsGraphics> getAllSavingsGraphics();
}
