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
    public WasteAccount getWasteAccountById(Long accountId) {
        return wasteAccountDAO.findById(accountId).orElse(null); // Use Optional for safety
    }

    @Override
    public WasteAccount createWasteAccount(WasteAccount account) {
        return wasteAccountDAO.save(account);
    }

    @Override
    public void deleteWasteAccount(Long accountId) {
        wasteAccountDAO.deleteById(accountId);
    }

    @Override
    public WasteAccount updateWasteAccount(Long accountId, WasteAccount updatedAccount) {
        WasteAccount existingAccount = wasteAccountDAO.findById(accountId).orElse(null);
        if (existingAccount != null) {
            existingAccount.setAddress(updatedAccount.getAddress());
            existingAccount.setResident(updatedAccount.getResident());
            existingAccount.setBills(updatedAccount.getBills());
            existingAccount.setWasteBins(updatedAccount.getWasteBins());
            return wasteAccountDAO.save(existingAccount);
        }
        return null; // or throw an exception if preferred
    }
}
