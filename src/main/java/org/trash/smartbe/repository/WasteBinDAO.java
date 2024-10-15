package org.trash.smartbe.repository;

import org.trash.smartbe.model.WasteBin;

import java.util.List;

public interface WasteBinDAO {
    List<WasteBin> findAll();
    WasteBin findById(String binId);
    WasteBin save(WasteBin wasteBin);
    void deleteById(String binId);
}