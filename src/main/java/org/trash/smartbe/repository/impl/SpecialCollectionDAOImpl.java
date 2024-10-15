package org.trash.smartbe.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.SpecialCollection;
import org.trash.smartbe.repository.SpecialCollectionDAO;

import java.util.List;

@Repository
@Transactional
public class SpecialCollectionDAOImpl implements SpecialCollectionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SpecialCollection> findAll() {
        return entityManager.createQuery("from SpecialCollection", SpecialCollection.class).getResultList();
    }

    @Override
    public SpecialCollection findById(String scid) {
        return entityManager.find(SpecialCollection.class, scid);
    }

    @Override
    public SpecialCollection save(SpecialCollection specialCollection) {
        entityManager.persist(specialCollection);
        return specialCollection;
    }

    @Override
    public void deleteById(String scid) {
        SpecialCollection specialCollection = findById(scid);
        if (specialCollection != null) {
            entityManager.remove(specialCollection);
        }
    }
}
