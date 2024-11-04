package pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

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


    @Column(length = 22 , nullable = false)
    private String name;




}
