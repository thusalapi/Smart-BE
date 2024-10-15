package org.trash.smartbe.repository;

import org.trash.smartbe.model.SpecialCollection;

import java.util.List;

public interface SpecialCollectionDAO {
    List<SpecialCollection> findAll();
    SpecialCollection findById(String scid);
    SpecialCollection save(SpecialCollection specialCollection);
    void deleteById(String scid);
}