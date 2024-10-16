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
    public WasteAccount getWasteAccountById(Long accountId) { // Changed from String to Long
        return wasteAccountDAO.findById(accountId);
    }

    @Override
    public WasteAccount createWasteAccount(WasteAccount account) {
        return wasteAccountDAO.save(account);
    }

    @Override
    public void deleteWasteAccount(Long accountId) { // Changed from String to Long
        wasteAccountDAO.deleteById(accountId);
    }

    @Override
    public WasteAccount updateWasteAccount(Long accountId, WasteAccount updatedAccount) { // Added update method
        WasteAccount existingAccount = wasteAccountDAO.findById(accountId);
        if (existingAccount != null) {
            existingAccount.setAddress(updatedAccount.getAddress());
            // Add any additional fields you may want to update here
            return wasteAccountDAO.save(existingAccount); // Save the updated account
        }
        return null; // or throw an exception if preferred
    }
}
