package pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.smartfinance.finzar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.entities.Category;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.entities.PeriodRecurrence;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates.Transaction;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "incomes")
@Getter
public class Income extends AuditableAbstractAggregateRoot<Income> {


    @Min(0)
    @NotNull
    @Column(name = "amount")
    private Double amount;

    //TODO: refactor Wallet to value object walletid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "period_recurrence_id")
    private PeriodRecurrence periodRecurrence;

    @ManyToMany
    @JoinTable(
            name = "income_transaction",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "income_id")
    )
    private Set<Transaction> transactions = new HashSet<>();

    public Income() {
    }

    public Income(Double amount, Wallet wallet, Category category, PeriodRecurrence periodRecurrence) {
        this.wallet = wallet;
        this.amount = amount;
        this.category = category;
        this.periodRecurrence = periodRecurrence;
    }

    public Income updateInformation(Double amount, Category category, PeriodRecurrence periodRecurrence) {
        this.amount = amount;
        this.category = category;
        this.periodRecurrence = periodRecurrence;
        return this;
    }

}
