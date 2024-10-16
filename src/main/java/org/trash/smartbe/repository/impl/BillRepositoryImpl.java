package org.trash.smartbe.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.Bill;
import org.trash.smartbe.repository.BillDAO;
import org.trash.smartbe.repository.BillRepository;

import java.util.List;

@Repository
public class BillRepositoryImpl implements BillRepository {

    @Autowired
    private BillDAO billDao;

    @Override
    public List<Bill> findByAmountBetween(float minAmount, float maxAmount) {
        return billDao.findAll().stream()
                .filter(bill -> bill.getAmount() >= minAmount && bill.getAmount() <= maxAmount)
                .toList();
    }
}
