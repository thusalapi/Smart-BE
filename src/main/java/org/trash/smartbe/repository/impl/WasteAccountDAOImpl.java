package org.trash.smartbe.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.WasteAccount;
import org.trash.smartbe.repository.WasteAccountDAO;

import java.util.List;

@Repository
@Transactional
public class WasteAccountDAOImpl implements WasteAccountDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WasteAccount> findAll() {
        return entityManager.createQuery("from WasteAccount", WasteAccount.class).getResultList();
    }

    @Override
    public WasteAccount findById(String accountId) {
        return entityManager.find(WasteAccount.class, accountId);
    }

    @Override
    public WasteAccount save(WasteAccount wasteAccount) {
        entityManager.persist(wasteAccount);
        return wasteAccount;
    }

    @Override
    public void deleteById(String accountId) {
        WasteAccount wasteAccount = findById(accountId);
        if (wasteAccount != null) {
            entityManager.remove(wasteAccount);
        }
    }
}
