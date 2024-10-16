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
    public WasteBin findById(Long binId) { // Changed from String to Long
        return entityManager.find(WasteBin.class, binId);
    }

    @Override
    public WasteBin save(WasteBin wasteBin) {
        entityManager.persist(wasteBin);
        return wasteBin;
    }

    @Override
    public void deleteById(Long binId) { // Changed from String to Long
        WasteBin wasteBin = findById(binId);
        if (wasteBin != null) {
            entityManager.remove(wasteBin);
        }
    }

    @Override
    public WasteBin update(Long binId, WasteBin updatedWasteBin) { // Added update method
        WasteBin existingWasteBin = findById(binId);
        if (existingWasteBin != null) {
            // Update fields of the existing waste bin
            existingWasteBin.setWasteType(updatedWasteBin.getWasteType());
            existingWasteBin.setMaxSize(updatedWasteBin.getMaxSize());
            existingWasteBin.setCurrentLevel(updatedWasteBin.getCurrentLevel());
            existingWasteBin.setWasteAccount(updatedWasteBin.getWasteAccount()); // If you want to update the associated WasteAccount

            // Merge the updated waste bin object
            entityManager.merge(existingWasteBin);
            return existingWasteBin;
        }
        return null; // or throw an exception if you prefer
    }
}
