package pe.edu.upc.smartfinance.finzar.cashflow.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "period_recurrences")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class PeriodRecurrence {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 22)
    private String name;



}
