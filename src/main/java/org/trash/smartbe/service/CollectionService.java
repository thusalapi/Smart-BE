package org.trash.smartbe.service;

import org.trash.smartbe.model.Collection;

import java.util.List;

public interface CollectionService {
    List<Collection> getAllCollections();
    Collection getCollectionById(Long collectionId); // Changed from String to Long
    Collection createCollection(Collection collection);
    void deleteCollection(Long collectionId); // Changed from String to Long
    Collection updateCollection(Long collectionId, Collection updatedCollection); // Added update method
}
