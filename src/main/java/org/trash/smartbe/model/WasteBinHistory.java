package org.trash.smartbe.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "waste_bin_history")
public class WasteBinHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "waste_bin_id", nullable = false)
    private WasteBin wasteBin;

    @Column(nullable = false)
    private Double wasteLevel;

    @Column(nullable = false)
    private LocalDateTime collectedAt;

    @Column(nullable = false)
    private String wasteCategory;
}
