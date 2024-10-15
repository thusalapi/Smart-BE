package org.trash.smartbe.service;

import org.trash.smartbe.model.WasteAccount;

import java.util.List;

public interface WasteAccountService {
    List<WasteAccount> getAllWasteAccounts();
    WasteAccount getWasteAccountById(String accountId);
    WasteAccount createWasteAccount(WasteAccount account);
    void deleteWasteAccount(String accountId);
}
