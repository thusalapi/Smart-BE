// BillRepository.java
package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trash.smartbe.model.Bill;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByWasteAccountId(Long wasteAccountId);
    List<Bill> findByIsPaid(boolean isPaid);
}