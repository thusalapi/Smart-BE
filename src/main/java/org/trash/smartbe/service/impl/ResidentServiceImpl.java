package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.model.Resident;
import org.trash.smartbe.repository.ResidentDAO;
import org.trash.smartbe.service.ResidentService;

import java.util.List;

@Service
public class ResidentServiceImpl implements ResidentService {

    private final ResidentDAO residentDAO;

    @Autowired
    public ResidentServiceImpl(ResidentDAO residentDAO) {
        this.residentDAO = residentDAO;
    }

    @Override
    public List<Resident> getAllResidents() {
        return residentDAO.findAll();
    }

    @Override
    public Resident getResidentById(Long residentId) { // Changed from String to Long
        return residentDAO.findById(residentId);
    }

    @Override
    public Resident createResident(Resident resident) {
        return residentDAO.save(resident);
    }

    @Override
    public void deleteResident(Long residentId) { // Changed from String to Long
        residentDAO.deleteById(residentId);
    }

    @Override
    public Resident updateResident(Long residentId, Resident updatedResident) { // Added update method
        Resident existingResident = residentDAO.findById(residentId);
        if (existingResident != null) {
            existingResident.setName(updatedResident.getName());
            existingResident.setContactInfo(updatedResident.getContactInfo());
            existingResident.setWasteAccount(updatedResident.getWasteAccount()); // Optional, depending on your use case
            existingResident.setSpecialCollections(updatedResident.getSpecialCollections()); // Optional, depending on your use case
            existingResident.setRegistrationDate(updatedResident.getRegistrationDate()); // Optional, depending on your use case
            return residentDAO.save(existingResident); // Save the updated resident
        }
        return null; // or throw an exception if preferred
    }
}
