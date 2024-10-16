package org.trash.smartbe.service;

import org.trash.smartbe.model.Resident;

import java.util.List;

public interface ResidentService {
    List<Resident> getAllResidents();
    Resident getResidentById(Long residentId); // Changed from String to Long
    Resident createResident(Resident resident);
    void deleteResident(Long residentId); // Changed from String to Long
    Resident updateResident(Long residentId, Resident updatedResident); // Added update method
}
