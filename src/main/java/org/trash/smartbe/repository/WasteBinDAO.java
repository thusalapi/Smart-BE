package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trash.smartbe.model.WasteBin;

public interface WasteBinDAO extends JpaRepository<WasteBin, Long> {
//    WasteBin findById(Long binId); // Custom method if necessary
}
