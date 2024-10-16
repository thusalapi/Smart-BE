package org.trash.smartbe.service;

import org.trash.smartbe.model.TruckDriver;

import java.util.List;

public interface TruckDriverService {
    List<TruckDriver> getAllTruckDrivers();
    TruckDriver getTruckDriverById(Long driverId); // Changed from String to Long
    TruckDriver createTruckDriver(TruckDriver driver);
    void deleteTruckDriver(Long driverId); // Changed from String to Long
    TruckDriver updateTruckDriver(Long driverId, TruckDriver updatedDriver); // Added update method
}
