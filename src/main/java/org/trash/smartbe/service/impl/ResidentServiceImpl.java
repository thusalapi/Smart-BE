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
    public Resident getResidentById(Long residentId) {
        return residentDAO.findById(residentId).orElse(null); // Use Optional for safety
    }

    @Override
    public Resident createResident(Resident resident) {
        return residentDAO.save(resident);
    }

    @Override
    public void deleteResident(Long residentId) {
        residentDAO.deleteById(residentId);
    }

    @Override
    public Resident updateResident(Long residentId, Resident updatedResident) {
        Resident existingResident = residentDAO.findById(residentId).orElse(null);
        if (existingResident != null) {
            existingResident.setName(updatedResident.getName());
            existingResident.setContactInfo(updatedResident.getContactInfo());
            existingResident.setWasteAccount(updatedResident.getWasteAccount());
            return residentDAO.save(existingResident);
        }
        return null; // or throw an exception if preferred
    }
}
