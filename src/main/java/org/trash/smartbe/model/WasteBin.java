package org.trash.smartbe.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "waste_bins")
public class WasteBin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String binNumber;

    @Column(nullable = false)
    private Double capacity;

    @Column(nullable = false)
    private Double currentLevel;

    @Column(nullable = false)
    private String wasteCategory;

    @Column(nullable = false)
    private Boolean isRecyclable;

    @ManyToOne
    @JoinColumn(name = "waste_account_id", nullable = false)
    private WasteAccount wasteAccount;
}