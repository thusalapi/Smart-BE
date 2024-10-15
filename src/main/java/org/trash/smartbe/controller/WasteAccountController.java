package org.trash.smartbe.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trash.smartbe.model.WasteAccount;
import org.trash.smartbe.service.WasteAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/waste-accounts")
public class WasteAccountController {

    @Autowired
    private WasteAccountService wasteAccountService;

    // Create a new Waste Account
    @PostMapping
    public ResponseEntity<WasteAccount> createWasteAccount(@RequestBody WasteAccount wasteAccount) {
        WasteAccount createdAccount = wasteAccountService.createWasteAccount(wasteAccount);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    // Get all Waste Accounts
    @GetMapping
    public ResponseEntity<List<WasteAccount>> getAllWasteAccounts() {
        List<WasteAccount> wasteAccounts = wasteAccountService.getAllWasteAccounts();
        return new ResponseEntity<>(wasteAccounts, HttpStatus.OK);
    }

    // Get a Waste Account by ID
    @GetMapping("/{id}")
    public ResponseEntity<WasteAccount> getWasteAccountById(@PathVariable String id) {
        WasteAccount wasteAccount = wasteAccountService.getWasteAccountById(id);
        return wasteAccount != null ? new ResponseEntity<>(wasteAccount, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update a Waste Account
//    @PutMapping("/{id}")
//    public ResponseEntity<WasteAccount> updateWasteAccount(@PathVariable Long id, @RequestBody WasteAccount wasteAccount) {
//        WasteAccount updatedAccount = wasteAccountService.updateWasteAccount(id, wasteAccount);
//        return updatedAccount != null ? new ResponseEntity<>(updatedAccount, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    // Delete a Waste Account
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteWasteAccount(@PathVariable String id) { // Change Long to String
//        boolean isDeleted = wasteAccountService.deleteWasteAccount(id);
//        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
