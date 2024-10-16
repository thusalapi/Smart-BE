// WasteAccountController.java
package org.trash.smartbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trash.smartbe.dto.WasteAccountDTO;
import org.trash.smartbe.service.WasteAccountService;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;

@RestController
@RequestMapping("/api/waste-accounts")
public class WasteAccountController {

    @Autowired
    private WasteAccountService wasteAccountService;

    @GetMapping
    public ResponseEntity<ResponseEntityDto> getAllWasteAccounts() {
        return ResponseEntity.ok(wasteAccountService.getAllWasteAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> getWasteAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(wasteAccountService.getWasteAccountById(id));
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<ResponseEntityDto> getWasteAccountByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(wasteAccountService.getWasteAccountByAccountNumber(accountNumber));
    }

    @PostMapping
    public ResponseEntity<ResponseEntityDto> createWasteAccount(@RequestBody WasteAccountDTO wasteAccountDTO) {
        return ResponseEntity.ok(wasteAccountService.createWasteAccount(wasteAccountDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> updateWasteAccount(@PathVariable Long id, @RequestBody WasteAccountDTO wasteAccountDTO) {
        return ResponseEntity.ok(wasteAccountService.updateWasteAccount(id, wasteAccountDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> deleteWasteAccount(@PathVariable Long id) {
        return ResponseEntity.ok(wasteAccountService.deleteWasteAccount(id));
    }
}