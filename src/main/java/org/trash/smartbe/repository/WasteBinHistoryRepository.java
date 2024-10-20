package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trash.smartbe.model.WasteBinHistory;

import java.util.List;

public interface WasteBinHistoryRepository extends JpaRepository<WasteBinHistory, Long> {
    List<WasteBinHistory> findByWasteBinId(Long wasteBinId);
}
