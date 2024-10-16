// ResidentRepository.java
package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trash.smartbe.model.Resident;

import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
    Optional<Resident> findByEmail(String email);
    Optional<Resident> findByWasteAccountId(Long wasteAccountId);
}