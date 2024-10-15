package org.trash.smartbe.service;

import org.trash.smartbe.model.WasteBin;

import java.util.List;

public interface WasteBinService {
    List<WasteBin> getAllWasteBins();
    WasteBin getWasteBinById(String binId);
    WasteBin createWasteBin(WasteBin bin);
    void deleteWasteBin(String binId);
}