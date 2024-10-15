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
    public WasteBin getWasteBinById(String binId) {
        return wasteBinDAO.findById(binId);
    }

    @Override
    public WasteBin createWasteBin(WasteBin bin) {
        return wasteBinDAO.save(bin);
    }

    @Override
    public void deleteWasteBin(String binId) {
        wasteBinDAO.deleteById(binId);
    }
}
