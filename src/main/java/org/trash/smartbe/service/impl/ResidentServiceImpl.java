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
    public Resident getResidentById(String residentId) {
        return residentDAO.findById(residentId);
    }

    @Override
    public Resident createResident(Resident resident) {
        return residentDAO.save(resident);
    }

    @Override
    public void deleteResident(String residentId) {
        residentDAO.deleteById(residentId);
    }
}
