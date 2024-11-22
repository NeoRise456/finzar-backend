package pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.valueobjects.TransactionTypes;

@Entity
@Table(name = "transaction_types")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 22)
    private TransactionTypes name;

    public TransactionType(TransactionTypes name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static TransactionType toTransactionTypeFromName(String name) {
        return new TransactionType(TransactionTypes.valueOf(name));
    }

}
