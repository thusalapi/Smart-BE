package org.trash.smartbe.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.Resident;
import org.trash.smartbe.repository.ResidentDAO;

import java.util.List;


@Repository
@Transactional
public class ResidentDAOImpl implements ResidentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Resident> findAll() {
        return entityManager.createQuery("from Resident", Resident.class).getResultList();
    }

    @Override
    public Resident findById(String residentId) {
        return entityManager.find(Resident.class, residentId);
    }

    @Override
    public Resident save(Resident resident) {
        entityManager.persist(resident);
        return resident;
    }

    @Override
    public void deleteById(String residentId) {
        Resident resident = findById(residentId);
        if (resident != null) {
            entityManager.remove(resident);
        }
    }
}