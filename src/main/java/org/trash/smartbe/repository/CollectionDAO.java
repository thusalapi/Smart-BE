package org.trash.smartbe.repository;

import org.trash.smartbe.model.Collection;

import java.util.List;

public interface CollectionDAO {
    List<Collection> findAll();
    Collection findById(Long collectionId);
    Collection save(Collection collection);
    Collection update(Long collectionId, Collection updatedCollection);
    void deleteById(Long collectionId);
}
