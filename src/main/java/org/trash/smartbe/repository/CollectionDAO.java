package org.trash.smartbe.repository;

import org.trash.smartbe.model.Collection;

import java.util.List;

public interface CollectionDAO {
    List<Collection> findAll();
    Collection findById(String collectionId);
    Collection save(Collection collection);
    void deleteById(String collectionId);
}
