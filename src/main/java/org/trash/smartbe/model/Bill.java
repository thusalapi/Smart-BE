// Bill.java
package org.trash.smartbe.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate billDate;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "waste_account_id", nullable = false)
    private WasteAccount wasteAccount;
}