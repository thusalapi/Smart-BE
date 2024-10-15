package org.trash.smartbe.service;

import org.trash.smartbe.model.SpecialCollection;

import java.util.List;

public interface SpecialCollectionService {
    List<SpecialCollection> getAllSpecialCollections();
    SpecialCollection getSpecialCollectionById(String collectionId);
    SpecialCollection createSpecialCollection(SpecialCollection collection);
    void deleteSpecialCollection(String collectionId);
}
