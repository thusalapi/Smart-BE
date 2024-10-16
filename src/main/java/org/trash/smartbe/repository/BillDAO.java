package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.Bill;

@Repository
public interface BillDAO extends JpaRepository<Bill, Long> {
//    Bill findById(Long billId); // Custom method if necessary
}
