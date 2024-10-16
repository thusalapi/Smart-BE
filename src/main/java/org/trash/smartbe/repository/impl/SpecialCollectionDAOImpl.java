package org.trash.smartbe.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.SpecialCollection;
import org.trash.smartbe.repository.SpecialCollectionDAO;

import java.util.List;

@Repository
@Transactional
public class SpecialCollectionDAOImpl implements SpecialCollectionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SpecialCollection> findAll() {
        return entityManager.createQuery("from SpecialCollection", SpecialCollection.class).getResultList();
    }

    @Override
    public SpecialCollection findById(Long scId) { // Changed from String to Long
        return entityManager.find(SpecialCollection.class, scId);
    }

    @Override
    public SpecialCollection save(SpecialCollection specialCollection) {
        entityManager.persist(specialCollection);
        return specialCollection;
    }

    @Override
    public void deleteById(Long scId) { // Changed from String to Long
        SpecialCollection specialCollection = findById(scId);
        if (specialCollection != null) {
            entityManager.remove(specialCollection);
        }
    }

    @Override
    public SpecialCollection update(Long scId, SpecialCollection updatedSpecialCollection) { // Changed from String to Long
        SpecialCollection existingSpecialCollection = findById(scId);
        if (existingSpecialCollection != null) {
            // Update the fields with the new values
            existingSpecialCollection.setAdditionalCost(updatedSpecialCollection.getAdditionalCost());
            existingSpecialCollection.setScheduledDate(updatedSpecialCollection.getScheduledDate());
            existingSpecialCollection.setResident(updatedSpecialCollection.getResident());

            // Merge the updated special collection object
            entityManager.merge(existingSpecialCollection);
            return existingSpecialCollection;
        }
        return null; // or throw an exception if you prefer
    }
}
