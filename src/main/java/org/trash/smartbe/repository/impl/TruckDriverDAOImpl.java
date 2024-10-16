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
    public TruckDriver findById(Long driverId) { // Changed from String to Long
        return entityManager.find(TruckDriver.class, driverId);
    }

    @Override
    public TruckDriver save(TruckDriver driver) {
        entityManager.persist(driver);
        return driver;
    }

    @Override
    public void deleteById(Long driverId) { // Changed from String to Long
        TruckDriver driver = findById(driverId);
        if (driver != null) {
            entityManager.remove(driver);
        }
    }

    @Override
    public TruckDriver update(Long driverId, TruckDriver updatedDriver) { // Added update method
        TruckDriver existingDriver = findById(driverId);
        if (existingDriver != null) {
            // Update the fields with the new values
            existingDriver.setName(updatedDriver.getName());
            existingDriver.setContactInfo(updatedDriver.getContactInfo());

            // Merge the updated driver object
            entityManager.merge(existingDriver);
            return existingDriver;
        }
        return null; // or throw an exception if you prefer
    }
}
