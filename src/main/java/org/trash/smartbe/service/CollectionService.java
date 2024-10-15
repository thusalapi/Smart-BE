package org.trash.smartbe.service;

import org.trash.smartbe.model.Collection;

import java.util.List;

public interface CollectionService {
    List<Collection> getAllCollections();
    Collection getCollectionById(String collectionId);
    Collection createCollection(Collection collection);
    void deleteCollection(String collectionId);
}
