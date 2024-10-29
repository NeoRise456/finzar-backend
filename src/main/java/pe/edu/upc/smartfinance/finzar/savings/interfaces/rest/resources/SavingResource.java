package pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.resources;

public record SavingResource(long id, int userId, String name, int totalGoal, int currentAmount, int categoryId, String startDate, String endDate) {
}
