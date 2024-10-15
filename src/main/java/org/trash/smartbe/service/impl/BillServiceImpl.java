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
    public Bill getBillById(String billId) {
        return billDAO.findById(billId);
    }

    @Override
    public Bill createBill(Bill bill) {
        return billDAO.save(bill);
    }

    @Override
    public void deleteBill(String billId) {
        billDAO.deleteById(billId);
    }
}
