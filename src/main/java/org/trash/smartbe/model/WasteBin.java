package org.trash.smartbe.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "waste_bin")
public class WasteBin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long binId;

    private String wasteType;
    private float maxSize;
    private float currentLevel;

    @ManyToOne
    @JoinColumn(name = "waste_account_id")
    private WasteAccount wasteAccount;

    // Getters, setters, constructors
}