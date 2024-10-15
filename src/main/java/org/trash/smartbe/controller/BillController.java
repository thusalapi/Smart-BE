package org.trash.smartbe.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trash.smartbe.model.Bill;
import org.trash.smartbe.service.BillService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    @Autowired
    private BillService billService;

    // Create a new Bill
    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        Bill createdBill = billService.createBill(bill);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }

    // Get all Bills
    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> bills = billService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    // Get a Bill by ID
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable String id) {
        Bill bill = billService.getBillById(id);
        return bill != null ? new ResponseEntity<>(bill, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update a Bill
//    @PutMapping("/{id}")
//    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill) {
//        Bill updatedBill = billService.updateBill(id, bill);
//        return updatedBill != null ? new ResponseEntity<>(updatedBill, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    // Delete a Bill
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBill(@PathVariable String id) {
//        if (billService.deleteBill(id)) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}