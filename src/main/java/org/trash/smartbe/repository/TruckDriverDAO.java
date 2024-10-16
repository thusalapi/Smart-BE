package org.trash.smartbe.repository;

import org.trash.smartbe.model.TruckDriver;

import java.util.List;

public interface TruckDriverDAO {
    List<TruckDriver> findAll();
    TruckDriver findById(Long driverId); // Changed from String to Long
    TruckDriver save(TruckDriver driver);
    void deleteById(Long driverId); // Changed from String to Long
    TruckDriver update(Long driverId, TruckDriver updatedDriver); // Added update method
}
