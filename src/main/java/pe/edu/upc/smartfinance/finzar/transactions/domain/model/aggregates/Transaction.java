package pe.edu.upc.smartfinance.finzar.transactions.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Expense;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Income;
import pe.edu.upc.smartfinance.finzar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities.TransactionType;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "transactions")
@NoArgsConstructor
public class Transaction extends AuditableAbstractAggregateRoot<Transaction> {

    //TODO: refactor Wallet to value object walletid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @Column(name = "note",columnDefinition = "text")
    private String note;

    @Min(0)
    @NotNull
    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Column(name = "transaction_date")
    private Date transactionDate;


    @ManyToMany(mappedBy = "transactions")
    private Set<Income> incomes = new HashSet<>();

    @ManyToMany(mappedBy = "transactions")
    private Set<Expense> expenses = new HashSet<>();

    public Transaction(
            Wallet wallet,
            TransactionType transactionType,
            String note,
            Double amount,
            Date transactionDate
    ) {
        this.wallet = wallet;
        this.transactionType = transactionType;
        this.note = note;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

}
