package org.trash.smartbe.service;

import org.trash.smartbe.model.TruckDriver;

import java.util.List;

public interface TruckDriverService {
    List<TruckDriver> getAllTruckDrivers();
    TruckDriver getTruckDriverById(String driverId);
    TruckDriver createTruckDriver(TruckDriver driver);
    void deleteTruckDriver(String driverId);
}
