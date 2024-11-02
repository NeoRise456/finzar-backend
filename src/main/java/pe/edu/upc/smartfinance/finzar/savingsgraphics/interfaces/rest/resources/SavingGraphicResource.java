package pe.edu.upc.smartfinance.finzar.savingsgraphics.interfaces.rest.resources;

import java.time.LocalDate;

public record SavingGraphicResource(int id, double totalGoal, double currentAmount, LocalDate startDate, LocalDate endDate) {
}
