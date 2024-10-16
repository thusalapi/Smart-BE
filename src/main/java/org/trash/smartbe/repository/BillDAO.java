package org.trash.smartbe.repository;

import org.trash.smartbe.model.Bill;

import java.util.List;

public interface BillDAO {
    List<Bill> findAll();
    Bill findById(Long billId);
    Bill save(Bill bill);
    Bill update(Long billId, Bill bill);
    void deleteById(Long billId);
}
