package org.trash.smartbe.repository.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.IOTDevice;
import org.trash.smartbe.repository.IOTDeviceDAO;

import java.util.List;

@Repository
@Transactional
public class IOTDeviceDAOImpl implements IOTDeviceDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<IOTDevice> findAll() {
        return entityManager.createQuery("from IOTDevice", IOTDevice.class).getResultList();
    }

    @Override
    public IOTDevice findById(String deviceId) {
        return entityManager.find(IOTDevice.class, deviceId);
    }

    @Override
    public IOTDevice save(IOTDevice iotDevice) {
        entityManager.persist(iotDevice);
        return iotDevice;
    }

    @Override
    public void deleteById(String deviceId) {
        IOTDevice iotDevice = findById(deviceId);
        if (iotDevice != null) {
            entityManager.remove(iotDevice);
        }
    }
}
