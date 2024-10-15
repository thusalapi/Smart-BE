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

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residentId;

    private String name;
    private String contactInfo;

    @OneToOne(cascade = CascadeType.ALL)
    private WasteAccount wasteAccount;

    // Special collections for a resident
    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private List<SpecialCollection> specialCollections;

    private Date registrationDate;


}

