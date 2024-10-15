package org.trash.smartbe.service;

import org.trash.smartbe.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAllBills();
    Bill getBillById(String billId);
    Bill createBill(Bill bill);
    void deleteBill(String billId);
}
