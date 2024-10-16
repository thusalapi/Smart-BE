// WasteAccountRepository.java
package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trash.smartbe.model.WasteAccount;

public interface WasteAccountRepository extends JpaRepository<WasteAccount, Long> {
    WasteAccount findByAccountNumber(String accountNumber);
}