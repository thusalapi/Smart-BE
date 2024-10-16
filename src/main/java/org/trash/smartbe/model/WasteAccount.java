// WasteAccount.java
package org.trash.smartbe.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "waste_accounts")
public class WasteAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @OneToMany(mappedBy = "wasteAccount", cascade = CascadeType.ALL)
    private List<Bill> bills;

    @OneToMany(mappedBy = "wasteAccount", cascade = CascadeType.ALL)
    private List<WasteBin> wasteBins;

    @OneToOne(mappedBy = "wasteAccount", cascade = CascadeType.ALL)
    private Resident resident;
}