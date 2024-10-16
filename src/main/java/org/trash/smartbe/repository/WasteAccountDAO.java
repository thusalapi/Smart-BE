package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trash.smartbe.model.WasteAccount;

public interface WasteAccountDAO extends JpaRepository<WasteAccount, Long> {
//    WasteAccount findById(Long accountId); // Custom method if necessary
}
