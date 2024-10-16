package org.trash.smartbe.service;

import org.trash.smartbe.model.WasteBin;

import java.util.List;

public interface WasteBinService {
    List<WasteBin> getAllWasteBins();
    WasteBin getWasteBinById(Long binId); // Changed from String to Long
    WasteBin createWasteBin(WasteBin bin);
    void deleteWasteBin(Long binId); // Changed from String to Long
    WasteBin updateWasteBin(Long binId, WasteBin updatedBin); // Added update method
}
