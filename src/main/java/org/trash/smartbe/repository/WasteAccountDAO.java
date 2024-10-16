package org.trash.smartbe.repository;

import org.trash.smartbe.model.WasteAccount;

import java.util.List;

public interface WasteAccountDAO {
    List<WasteAccount> findAll();
    WasteAccount findById(Long accountId); // Changed from String to Long
    WasteAccount save(WasteAccount wasteAccount);
    void deleteById(Long accountId); // Changed from String to Long
    WasteAccount update(Long accountId, WasteAccount updatedWasteAccount); // Added update method
}
