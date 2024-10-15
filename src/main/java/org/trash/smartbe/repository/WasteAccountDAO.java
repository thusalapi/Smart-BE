package org.trash.smartbe.repository;

import org.trash.smartbe.model.WasteAccount;

import java.util.List;

public interface WasteAccountDAO {
    List<WasteAccount> findAll();
    WasteAccount findById(String accountId);
    WasteAccount save(WasteAccount wasteAccount);
    void deleteById(String accountId);
}