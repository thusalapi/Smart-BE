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
    public Bill getBillById(Long billId) {
        return billDAO.findById(billId).orElse(null);
    }

    @Override
    public Bill createBill(Bill bill) {
        return billDAO.save(bill);
    }

    @Override
    public void deleteBill(Long billId) {
        billDAO.deleteById(billId);
    }

    @Override
    public Bill updateBill(Long billId, Bill updatedBill) {
        Bill existingBill = billDAO.findById(billId).orElse(null);
        if (existingBill != null) {
            existingBill.setIssueDate(updatedBill.getIssueDate());
            existingBill.setAmount(updatedBill.getAmount());
            existingBill.setWasteAccount(updatedBill.getWasteAccount());
            return billDAO.save(existingBill); // Save the updated bill
        }
        return null; // or throw an exception if preferred
    }
}
