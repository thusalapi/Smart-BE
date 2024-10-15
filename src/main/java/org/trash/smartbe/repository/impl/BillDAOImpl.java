package org.trash.smartbe.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.Bill;
import org.trash.smartbe.repository.BillDAO;

import java.util.List;

@Repository
@Transactional
public class BillDAOImpl implements BillDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Bill> findAll() {
        return entityManager.createQuery("from Bill", Bill.class).getResultList();
    }

    @Override
    public Bill findById(String billId) {
        return entityManager.find(Bill.class, billId);
    }

    @Override
    public Bill save(Bill bill) {
        entityManager.persist(bill);
        return bill;
    }

    @Override
    public void deleteById(String billId) {
        Bill bill = findById(billId);
        if (bill != null) {
            entityManager.remove(bill);
        }
    }
}