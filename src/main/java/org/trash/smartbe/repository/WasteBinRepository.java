package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trash.smartbe.model.WasteBin;

import java.util.List;

public interface WasteBinRepository extends JpaRepository<WasteBin, Long> {
    List<WasteBin> findByWasteAccountId(Long wasteAccountId);
    WasteBin findByBinNumber(String binNumber);
    List<WasteBin> findByWasteCategory(String wasteCategory); // Updated to 'wasteCategory'
}
