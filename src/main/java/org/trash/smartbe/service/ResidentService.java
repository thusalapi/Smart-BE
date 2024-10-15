package org.trash.smartbe.service;

import org.trash.smartbe.model.Resident;

import java.util.List;

public interface ResidentService {
    List<Resident> getAllResidents();
    Resident getResidentById(String residentId);
    Resident createResident(Resident resident);
    void deleteResident(String residentId);
}
