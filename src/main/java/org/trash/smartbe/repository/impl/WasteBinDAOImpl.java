package org.trash.smartbe.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.WasteBin;
import org.trash.smartbe.repository.WasteBinDAO;

import java.util.List;

@Repository
@Transactional
public class WasteBinDAOImpl implements WasteBinDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WasteBin> findAll() {
        return entityManager.createQuery("from WasteBin", WasteBin.class).getResultList();
    }

    @Override
    public WasteBin findById(String binId) {
        return entityManager.find(WasteBin.class, binId);
    }

    @Override
    public WasteBin save(WasteBin wasteBin) {
        entityManager.persist(wasteBin);
        return wasteBin;
    }

    @Override
    public void deleteById(String binId) {
        WasteBin wasteBin = findById(binId);
        if (wasteBin != null) {
            entityManager.remove(wasteBin);
        }
    }
}
