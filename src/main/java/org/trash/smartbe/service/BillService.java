package org.trash.smartbe.service;

import org.trash.smartbe.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAllBills();
    Bill getBillById(Long billId);
    Bill createBill(Bill bill);
    void deleteBill(Long billId);
    Bill updateBill(Long billId, Bill updatedBill); // Update method
}
