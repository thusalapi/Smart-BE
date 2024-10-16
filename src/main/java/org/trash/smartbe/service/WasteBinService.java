package org.trash.smartbe.service;

import org.trash.smartbe.model.WasteBin;

import java.util.List;

public interface WasteBinService {
    List<WasteBin> getAllWasteBins();
    WasteBin getWasteBinById(Long binId);
    WasteBin createWasteBin(WasteBin bin);
    void deleteWasteBin(Long binId);
    WasteBin updateWasteBin(Long binId, WasteBin updatedBin); // Update method
}
