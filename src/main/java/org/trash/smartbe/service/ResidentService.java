package org.trash.smartbe.service;

import org.trash.smartbe.model.Resident;

import java.util.List;

public interface ResidentService {
    List<Resident> getAllResidents();
    Resident getResidentById(Long residentId);
    Resident createResident(Resident resident);
    void deleteResident(Long residentId);
    Resident updateResident(Long residentId, Resident updatedResident); // Update method
}
