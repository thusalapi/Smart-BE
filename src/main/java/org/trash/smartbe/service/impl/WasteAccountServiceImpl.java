package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.model.WasteAccount;
import org.trash.smartbe.repository.WasteAccountDAO;
import org.trash.smartbe.service.WasteAccountService;

import java.util.List;

@Service
public class WasteAccountServiceImpl implements WasteAccountService {

    private final WasteAccountDAO wasteAccountDAO;

    @Autowired
    public WasteAccountServiceImpl(WasteAccountDAO wasteAccountDAO) {
        this.wasteAccountDAO = wasteAccountDAO;
    }

    @Override
    public List<WasteAccount> getAllWasteAccounts() {
        return wasteAccountDAO.findAll();
    }

    @Override
    public WasteAccount getWasteAccountById(String accountId) {
        return wasteAccountDAO.findById(accountId);
    }

    @Override
    public WasteAccount createWasteAccount(WasteAccount account) {
        return wasteAccountDAO.save(account);
    }

    @Override
    public void deleteWasteAccount(String accountId) {
        wasteAccountDAO.deleteById(accountId);
    }
}