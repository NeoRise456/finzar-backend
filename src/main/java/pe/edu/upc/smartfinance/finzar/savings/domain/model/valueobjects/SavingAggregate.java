package pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects;

import lombok.Getter;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving;

@Getter
public class SavingAggregate {

    private final Saving saving;

    public SavingAggregate(Saving saving){
        this.saving = saving;
    }

    public void addAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to add must be positive.");
        }
        int updatedAmount = this.saving.getCurrentAmount() + amount;
        if (updatedAmount > this.saving.getTotalGoal()) {
            throw new IllegalStateException("Current amount exceeds total goal.");
        }
        this.saving.setCurrentAmount(updatedAmount);
    }

    public boolean isGoalReached() {
        return this.saving.getCurrentAmount() >= this.saving.getTotalGoal();
    }

}
