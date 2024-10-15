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
    public Collection getCollectionById(String collectionId) {
        return collectionDAO.findById(collectionId);
    }

    @Override
    public Collection createCollection(Collection collection) {
        return collectionDAO.save(collection);
    }

    @Override
    public void deleteCollection(String collectionId) {
        collectionDAO.deleteById(collectionId);
    }
}
