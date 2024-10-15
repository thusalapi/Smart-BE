package org.trash.smartbe.repository;

import org.trash.smartbe.model.TruckDriver;

import java.util.List;

public interface TruckDriverDAO {
    List<TruckDriver> findAll();
    TruckDriver findById(String driverId);
    TruckDriver save(TruckDriver driver);
    void deleteById(String driverId);
}