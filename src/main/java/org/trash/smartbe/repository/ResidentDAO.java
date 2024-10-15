package org.trash.smartbe.repository;

import org.trash.smartbe.model.Resident;

import java.util.List;

public interface ResidentDAO {
    List<Resident> findAll();
    Resident findById(String residentId);
    Resident save(Resident resident);
    void deleteById(String residentId);
}
