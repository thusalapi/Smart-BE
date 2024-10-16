package org.trash.smartbe.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.Collection;
import org.trash.smartbe.repository.CollectionDAO;

import java.util.List;

@Repository
@Transactional
public class CollectionDAOImpl implements CollectionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Collection> findAll() {
        return entityManager.createQuery("from Collection", Collection.class).getResultList();
    }

    @Override
    public Collection findById(Long collectionId) {
        return entityManager.find(Collection.class, collectionId);
    }

    @Override
    public Collection save(Collection collection) {
        entityManager.persist(collection);
        return collection;
    }

    @Override
    public Collection update(Long collectionId, Collection updatedCollection) {
        Collection existingCollection = findById(collectionId);
        if (existingCollection != null) {
            existingCollection.setCollectedDate(updatedCollection.getCollectedDate());
            existingCollection.setWasteBin(updatedCollection.getWasteBin());

            entityManager.merge(existingCollection);
            return existingCollection;
        }
        return null;
    }

    @Override
    public void deleteById(Long collectionId) {
        Collection collection = findById(collectionId);
        if (collection != null) {
            entityManager.remove(collection);
        }
    }
}
