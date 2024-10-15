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
    public TruckDriver getTruckDriverById(String driverId) {
        return truckDriverDAO.findById(driverId);
    }

    @Override
    public TruckDriver createTruckDriver(TruckDriver driver) {
        return truckDriverDAO.save(driver);
    }

    @Override
    public void deleteTruckDriver(String driverId) {
        truckDriverDAO.deleteById(driverId);
    }
}
