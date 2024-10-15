package org.trash.smartbe.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.TruckDriver;
import org.trash.smartbe.repository.TruckDriverDAO;

import java.util.List;

@Repository
@Transactional
public class TruckDriverDAOImpl implements TruckDriverDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TruckDriver> findAll() {
        return entityManager.createQuery("from TruckDriver", TruckDriver.class).getResultList();
    }

    @Override
    public TruckDriver findById(String driverId) {
        return entityManager.find(TruckDriver.class, driverId);
    }

    @Override
    public TruckDriver save(TruckDriver driver) {
        entityManager.persist(driver);
        return driver;
    }

    @Override
    public void deleteById(String driverId) {
        TruckDriver driver = findById(driverId);
        if (driver != null) {
            entityManager.remove(driver);
        }
    }
}
