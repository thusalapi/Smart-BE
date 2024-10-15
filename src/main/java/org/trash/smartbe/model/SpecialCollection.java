package org.trash.smartbe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class SpecialCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scId;

    private float additionalCost;
    private Date scheduledDate;

    @ManyToOne
    private Resident resident;

}
