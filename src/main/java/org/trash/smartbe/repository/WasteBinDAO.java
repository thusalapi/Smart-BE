package org.trash.smartbe.repository;

import org.trash.smartbe.model.WasteBin;

import java.util.List;

public interface WasteBinDAO {
    List<WasteBin> findAll();
    WasteBin findById(Long binId); // Changed from String to Long
    WasteBin save(WasteBin wasteBin);
    void deleteById(Long binId); // Changed from String to Long
    WasteBin update(Long binId, WasteBin updatedWasteBin); // Added update method
}
