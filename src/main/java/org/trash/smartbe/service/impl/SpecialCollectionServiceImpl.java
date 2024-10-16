package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.model.SpecialCollection;
import org.trash.smartbe.repository.SpecialCollectionDAO;
import org.trash.smartbe.service.SpecialCollectionService;

import java.util.List;

@Service
public class SpecialCollectionServiceImpl implements SpecialCollectionService {

    private final SpecialCollectionDAO specialCollectionDAO;

    @Autowired
    public SpecialCollectionServiceImpl(SpecialCollectionDAO specialCollectionDAO) {
        this.specialCollectionDAO = specialCollectionDAO;
    }

    @Override
    public List<SpecialCollection> getAllSpecialCollections() {
        return specialCollectionDAO.findAll();
    }

    @Override
    public SpecialCollection getSpecialCollectionById(Long collectionId) { // Changed from String to Long
        return specialCollectionDAO.findById(collectionId);
    }

    @Override
    public SpecialCollection createSpecialCollection(SpecialCollection collection) {
        return specialCollectionDAO.save(collection);
    }

    @Override
    public void deleteSpecialCollection(Long collectionId) { // Changed from String to Long
        specialCollectionDAO.deleteById(collectionId);
    }

    @Override
    public SpecialCollection updateSpecialCollection(Long collectionId, SpecialCollection updatedCollection) { // Added update method
        SpecialCollection existingCollection = specialCollectionDAO.findById(collectionId);
        if (existingCollection != null) {
            existingCollection.setAdditionalCost(updatedCollection.getAdditionalCost());
            existingCollection.setScheduledDate(updatedCollection.getScheduledDate());
            existingCollection.setResident(updatedCollection.getResident()); // Optional, depending on your use case
            return specialCollectionDAO.save(existingCollection); // Save the updated collection
        }
        return null; // or throw an exception if preferred
    }
}
