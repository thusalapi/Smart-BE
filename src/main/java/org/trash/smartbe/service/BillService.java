package org.trash.smartbe.service;

import org.trash.smartbe.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAllBills();
    Bill getBillById(Long billId); // Changed from String to Long
    Bill createBill(Bill bill);
    void deleteBill(Long billId); // Changed from String to Long
    Bill updateBill(Long billId, Bill updatedBill); // Added update method
}
