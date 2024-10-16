package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.model.TruckDriver;
import org.trash.smartbe.repository.TruckDriverDAO;
import org.trash.smartbe.service.TruckDriverService;

import java.util.List;

@Service
public class TruckDriverServiceImpl implements TruckDriverService {

    private final TruckDriverDAO truckDriverDAO;

    @Autowired
    public TruckDriverServiceImpl(TruckDriverDAO truckDriverDAO) {
        this.truckDriverDAO = truckDriverDAO;
    }

    @Override
    public List<TruckDriver> getAllTruckDrivers() {
        return truckDriverDAO.findAll();
    }

    @Override
    public TruckDriver getTruckDriverById(Long driverId) { // Changed from String to Long
        return truckDriverDAO.findById(driverId);
    }

    @Override
    public TruckDriver createTruckDriver(TruckDriver driver) {
        return truckDriverDAO.save(driver);
    }

    @Override
    public void deleteTruckDriver(Long driverId) { // Changed from String to Long
        truckDriverDAO.deleteById(driverId);
    }

    @Override
    public TruckDriver updateTruckDriver(Long driverId, TruckDriver updatedDriver) { // Added update method
        TruckDriver existingDriver = truckDriverDAO.findById(driverId);
        if (existingDriver != null) {
            existingDriver.setName(updatedDriver.getName());
            existingDriver.setContactInfo(updatedDriver.getContactInfo());
            return truckDriverDAO.save(existingDriver); // Save the updated driver
        }
        return null; // or throw an exception if preferred
    }
}
