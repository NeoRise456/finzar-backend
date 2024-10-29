package pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.resources;

public record CreateSavingResource(String name, int totalGoal, int currentAmount, int startDate, int endDate) {
}
