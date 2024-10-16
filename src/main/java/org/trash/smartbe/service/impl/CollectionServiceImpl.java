package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.model.Collection;
import org.trash.smartbe.repository.CollectionDAO;
import org.trash.smartbe.service.CollectionService;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionDAO collectionDAO;

    @Autowired
    public CollectionServiceImpl(CollectionDAO collectionDAO) {
        this.collectionDAO = collectionDAO;
    }

    @Override
    public List<Collection> getAllCollections() {
        return collectionDAO.findAll();
    }

    @Override
    public Collection getCollectionById(Long collectionId) { // Changed from String to Long
        return collectionDAO.findById(collectionId);
    }

    @Override
    public Collection createCollection(Collection collection) {
        return collectionDAO.save(collection);
    }

    @Override
    public void deleteCollection(Long collectionId) { // Changed from String to Long
        collectionDAO.deleteById(collectionId);
    }

    @Override
    public Collection updateCollection(Long collectionId, Collection updatedCollection) { // Added update method
        Collection existingCollection = collectionDAO.findById(collectionId);
        if (existingCollection != null) {
            // Update fields from updatedCollection to existingCollection
            existingCollection.setCollectedDate(updatedCollection.getCollectedDate());
            existingCollection.setWasteBin(updatedCollection.getWasteBin()); // Optional, depending on your use case
            return collectionDAO.save(existingCollection); // Save the updated collection
        }
        return null; // or throw an exception if preferred
    }
}
