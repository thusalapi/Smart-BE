package org.trash.smartbe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "waste_bin")
public class WasteBin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long binId;

    private String wasteType;
    private float maxSize;
    private float currentLevel;

    @OneToOne
    @JoinColumn(name = "waste_account_id")
    private WasteAccount wasteAccount; // Assuming a many-to-one relationship with WasteAccount

    // Getters, setters, constructors
}
