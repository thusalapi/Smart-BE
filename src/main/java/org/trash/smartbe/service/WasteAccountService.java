package org.trash.smartbe.service;

import org.trash.smartbe.model.WasteAccount;

import java.util.List;

public interface WasteAccountService {
    List<WasteAccount> getAllWasteAccounts();
    WasteAccount getWasteAccountById(Long accountId); // Changed from String to Long
    WasteAccount createWasteAccount(WasteAccount account);
    void deleteWasteAccount(Long accountId); // Changed from String to Long
    WasteAccount updateWasteAccount(Long accountId, WasteAccount updatedAccount); // Added update method
}
