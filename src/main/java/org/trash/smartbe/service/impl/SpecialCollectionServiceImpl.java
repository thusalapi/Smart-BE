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
    public SpecialCollection getSpecialCollectionById(String collectionId) {
        return specialCollectionDAO.findById(collectionId);
    }

    @Override
    public SpecialCollection createSpecialCollection(SpecialCollection collection) {
        return specialCollectionDAO.save(collection);
    }

    @Override
    public void deleteSpecialCollection(String collectionId) {
        specialCollectionDAO.deleteById(collectionId);
    }
}
