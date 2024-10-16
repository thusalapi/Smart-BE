package org.trash.smartbe.service;

import org.trash.smartbe.model.SpecialCollection;

import java.util.List;

public interface SpecialCollectionService {
    List<SpecialCollection> getAllSpecialCollections();
    SpecialCollection getSpecialCollectionById(Long collectionId); // Changed from String to Long
    SpecialCollection createSpecialCollection(SpecialCollection collection);
    void deleteSpecialCollection(Long collectionId); // Changed from String to Long
    SpecialCollection updateSpecialCollection(Long collectionId, SpecialCollection updatedCollection); // Added update method
}
