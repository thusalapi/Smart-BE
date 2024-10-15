package org.trash.smartbe.repository;

import org.trash.smartbe.model.Bill;

import java.util.List;

public interface BillDAO {
    List<Bill> findAll();
    Bill findById(String billId);
    Bill save(Bill bill);
    void deleteById(String billId);
}
