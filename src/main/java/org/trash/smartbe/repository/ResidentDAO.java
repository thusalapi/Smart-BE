package org.trash.smartbe.repository;

import org.trash.smartbe.model.Resident;

import java.util.List;

public interface ResidentDAO {
    List<Resident> findAll();
    Resident findById(Long residentId);
    Resident save(Resident resident);
    void deleteById(Long residentId);
    Resident update(Long residentId, Resident updatedResident);
}
