package org.trash.smartbe.service;

import org.trash.smartbe.model.WasteAccount;

import java.util.List;

public interface WasteAccountService {
    List<WasteAccount> getAllWasteAccounts();
    WasteAccount getWasteAccountById(Long accountId);
    WasteAccount createWasteAccount(WasteAccount account);
    void deleteWasteAccount(Long accountId);
    WasteAccount updateWasteAccount(Long accountId, WasteAccount updatedAccount); // Update method
}
