package org.trash.smartbe.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class WasteAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String address;

    @OneToOne(mappedBy = "wasteAccount", cascade = CascadeType.ALL)
    private Resident resident;

    @OneToMany(mappedBy = "wasteAccount", cascade = CascadeType.ALL)
    private List<Bill> bills;

    @OneToMany(mappedBy = "wasteAccount", cascade = CascadeType.ALL)
    private List<WasteBin> wasteBins;

}