// WasteBinRepository.java
package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trash.smartbe.model.WasteBin;

import java.util.List;

public interface WasteBinRepository extends JpaRepository<WasteBin, Long> {
    List<WasteBin> findByWasteAccountId(Long wasteAccountId);
    WasteBin findByBinNumber(String binNumber);
    List<WasteBin> findByWasteType(String wasteType);

    @Modifying
    @Query("UPDATE WasteBin w SET w.currentLevel = 0 WHERE w.id = :wasteBinId")
    void updateCurrentLevelToZero(@Param("wasteBinId") Long wasteBinId);
}