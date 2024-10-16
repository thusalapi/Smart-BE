package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trash.smartbe.model.Resident;

public interface ResidentDAO extends JpaRepository<Resident, Long> {
//    Resident findById(Long residentId); // Custom method if necessary
}
