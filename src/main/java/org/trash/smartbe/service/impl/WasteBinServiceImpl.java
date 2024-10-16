package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.model.WasteBin;
import org.trash.smartbe.repository.WasteBinDAO;
import org.trash.smartbe.service.WasteBinService;

import java.util.List;

@Service
public class WasteBinServiceImpl implements WasteBinService {

    private final WasteBinDAO wasteBinDAO;

    @Autowired
    public WasteBinServiceImpl(WasteBinDAO wasteBinDAO) {
        this.wasteBinDAO = wasteBinDAO;
    }

    @Override
    public List<WasteBin> getAllWasteBins() {
        return wasteBinDAO.findAll();
    }

    @Override
    public WasteBin getWasteBinById(Long binId) {
        return wasteBinDAO.findById(binId).orElse(null); // Use Optional for safety
    }

    @Override
    public WasteBin createWasteBin(WasteBin bin) {
        return wasteBinDAO.save(bin);
    }

    @Override
    public void deleteWasteBin(Long binId) {
        wasteBinDAO.deleteById(binId);
    }

    @Override
    public WasteBin updateWasteBin(Long binId, WasteBin updatedBin) {
        WasteBin existingBin = wasteBinDAO.findById(binId).orElse(null);
        if (existingBin != null) {
            existingBin.setWasteType(updatedBin.getWasteType());
            existingBin.setMaxSize(updatedBin.getMaxSize());
            existingBin.setCurrentLevel(updatedBin.getCurrentLevel());
            existingBin.setWasteAccount(updatedBin.getWasteAccount());
            return wasteBinDAO.save(existingBin);
        }
        return null; // or throw an exception if preferred
    }
}
