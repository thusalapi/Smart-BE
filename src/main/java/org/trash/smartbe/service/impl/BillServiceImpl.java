package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.model.Bill;
import org.trash.smartbe.repository.BillDAO;
import org.trash.smartbe.service.BillService;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillDAO billDAO;

    @Autowired
    public BillServiceImpl(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    @Override
    public List<Bill> getAllBills() {
        return billDAO.findAll();
    }

    @Override
    public Bill getBillById(Long billId) { // Changed from String to Long
        return billDAO.findById(billId);
    }

    @Override
    public Bill createBill(Bill bill) {
        return billDAO.save(bill);
    }

    @Override
    public void deleteBill(Long billId) { // Changed from String to Long
        billDAO.deleteById(billId);
    }

    @Override
    public Bill updateBill(Long billId, Bill updatedBill) { // Added update method
        Bill existingBill = billDAO.findById(billId);
        if (existingBill != null) {
            // Update fields from updatedBill to existingBill here
            existingBill.setDate(updatedBill.getDate());
            existingBill.setAmount(updatedBill.getAmount());
            existingBill.setWasteAccount(updatedBill.getWasteAccount()); // Optional, depending on your use case
            return billDAO.save(existingBill); // Save the updated bill
        }
        return null; // or throw an exception if preferred
    }
}
