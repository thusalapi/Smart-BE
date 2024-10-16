package org.trash.smartbe.repository;

import org.trash.smartbe.model.SpecialCollection;

import java.util.List;

public interface SpecialCollectionDAO {
    List<SpecialCollection> findAll();
    SpecialCollection findById(Long scId); // Changed from String to Long
    SpecialCollection save(SpecialCollection specialCollection);
    void deleteById(Long scId); // Changed from String to Long
    SpecialCollection update(Long scId, SpecialCollection updatedSpecialCollection); // Changed from String to Long
}
