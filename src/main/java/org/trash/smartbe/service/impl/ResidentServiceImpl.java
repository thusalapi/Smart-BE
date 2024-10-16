// ResidentServiceImpl.java
package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.dto.ResidentDTO;
import org.trash.smartbe.model.Resident;
import org.trash.smartbe.model.WasteAccount;
import org.trash.smartbe.repository.ResidentRepository;
import org.trash.smartbe.repository.WasteAccountRepository;
import org.trash.smartbe.service.ResidentService;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.util.DTOConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private WasteAccountRepository wasteAccountRepository;

    @Autowired
    private DTOConverter dtoConverter;

    @Override
    public ResponseEntityDto getAllResidents() {
        List<ResidentDTO> residents = residentRepository.findAll().stream()
                .map(dtoConverter::convertToResidentDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, residents);
    }

    @Override
    public ResponseEntityDto getResidentById(Long id) {
        Resident resident = residentRepository.findById(id).orElse(null);
        if (resident == null) {
            return new ResponseEntityDto("Resident not found", true);
        }
        return new ResponseEntityDto(false, dtoConverter.convertToResidentDTO(resident));
    }

    @Override
    public ResponseEntityDto getResidentByEmail(String email) {
        Resident resident = residentRepository.findByEmail(email).orElse(null);
        if (resident == null) {
            return new ResponseEntityDto("Resident not found", true);
        }
        return new ResponseEntityDto(false, dtoConverter.convertToResidentDTO(resident));
    }

    @Override
    public ResponseEntityDto getResidentByWasteAccountId(Long wasteAccountId) {
        Resident resident = residentRepository.findByWasteAccountId(wasteAccountId).orElse(null);
        if (resident == null) {
            return new ResponseEntityDto("Resident not found", true);
        }
        return new ResponseEntityDto(false, dtoConverter.convertToResidentDTO(resident));
    }

    @Override
    public ResponseEntityDto createResident(ResidentDTO residentDTO) {
        if (residentRepository.findByEmail(residentDTO.getEmail()).isPresent()) {
            return new ResponseEntityDto("Email already in use", true);
        }

        Resident resident = dtoConverter.convertToResident(residentDTO);

        if (residentDTO.getWasteAccountId() != null) {
            WasteAccount wasteAccount = wasteAccountRepository.findById(residentDTO.getWasteAccountId())
                    .orElse(null);
            if (wasteAccount == null) {
                return new ResponseEntityDto("WasteAccount not found", true);
            }
            resident.setWasteAccount(wasteAccount);
        }

        Resident savedResident = residentRepository.save(resident);
        return new ResponseEntityDto(false, dtoConverter.convertToResidentDTO(savedResident));
    }

    @Override
    public ResponseEntityDto updateResident(Long id, ResidentDTO residentDTO) {
        Resident existingResident = residentRepository.findById(id).orElse(null);
        if (existingResident == null) {
            return new ResponseEntityDto("Resident not found", true);
        }

        if (!existingResident.getEmail().equals(residentDTO.getEmail()) &&
                residentRepository.findByEmail(residentDTO.getEmail()).isPresent()) {
            return new ResponseEntityDto("Email already in use", true);
        }

        existingResident.setFirstName(residentDTO.getFirstName());
        existingResident.setLastName(residentDTO.getLastName());
        existingResident.setEmail(residentDTO.getEmail());
        existingResident.setPhone(residentDTO.getPhone());
        existingResident.setAddress(residentDTO.getAddress());

        if (residentDTO.getWasteAccountId() != null &&
                !residentDTO.getWasteAccountId().equals(existingResident.getWasteAccount().getId())) {
            WasteAccount newWasteAccount = wasteAccountRepository.findById(residentDTO.getWasteAccountId())
                    .orElse(null);
            if (newWasteAccount == null) {
                return new ResponseEntityDto("New WasteAccount not found", true);
            }
            existingResident.setWasteAccount(newWasteAccount);
        }

        Resident updatedResident = residentRepository.save(existingResident);
        return new ResponseEntityDto(false, dtoConverter.convertToResidentDTO(updatedResident));
    }

    @Override
    public ResponseEntityDto deleteResident(Long id) {
        if (!residentRepository.existsById(id)) {
            return new ResponseEntityDto("Resident not found", true);
        }
        residentRepository.deleteById(id);
        return new ResponseEntityDto("Resident deleted successfully", false);
    }
}