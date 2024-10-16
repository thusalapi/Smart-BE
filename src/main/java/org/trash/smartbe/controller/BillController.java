// BillController.java
package org.trash.smartbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trash.smartbe.dto.BillDTO;
import org.trash.smartbe.service.BillService;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<ResponseEntityDto> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> getBillById(@PathVariable Long id) {
        return ResponseEntity.ok(billService.getBillById(id));
    }

    @GetMapping("/waste-account/{wasteAccountId}")
    public ResponseEntity<ResponseEntityDto> getBillsByWasteAccountId(@PathVariable Long wasteAccountId) {
        return ResponseEntity.ok(billService.getBillsByWasteAccountId(wasteAccountId));
    }

    @GetMapping("/payment-status")
    public ResponseEntity<ResponseEntityDto> getBillsByPaymentStatus(@RequestParam boolean isPaid) {
        return ResponseEntity.ok(billService.getBillsByPaymentStatus(isPaid));
    }

    @PostMapping
    public ResponseEntity<ResponseEntityDto> createBill(@RequestBody BillDTO billDTO) {
        return ResponseEntity.ok(billService.createBill(billDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> updateBill(@PathVariable Long id, @RequestBody BillDTO billDTO) {
        return ResponseEntity.ok(billService.updateBill(id, billDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> deleteBill(@PathVariable Long id) {
        return ResponseEntity.ok(billService.deleteBill(id));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<ResponseEntityDto> payBill(@PathVariable Long id) {
        return ResponseEntity.ok(billService.payBill(id));
    }
}