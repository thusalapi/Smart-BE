package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.dto.SpecialCollectionDTO;
import org.trash.smartbe.model.SpecialCollection;
import org.trash.smartbe.model.WasteAccount;
import org.trash.smartbe.repository.SpecialCollectionRepository;
import org.trash.smartbe.repository.WasteAccountRepository;
import org.trash.smartbe.service.SpecialCollectionService;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialCollectionServiceImpl implements SpecialCollectionService {

    @Autowired
    private SpecialCollectionRepository specialCollectionRepository;

    @Autowired
    private WasteAccountRepository wasteAccountRepository;

    @Override
    public ResponseEntityDto getAllSpecialCollections() {
        List<SpecialCollection> specialCollections = specialCollectionRepository.findAll();
        List<SpecialCollectionDTO> specialCollectionDTOs = specialCollections.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, specialCollectionDTOs);
    }

    @Override
    public ResponseEntityDto getSpecialCollectionById(Long id) {
        try {
            SpecialCollection specialCollection = specialCollectionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Special collection not found"));
            return new ResponseEntityDto(false, convertToDTO(specialCollection));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntityDto(e.getMessage(), true);
        }
    }

    @Override
    public ResponseEntityDto getSpecialCollectionsByWasteAccountId(Long wasteAccountId) {
        List<SpecialCollection> specialCollections = specialCollectionRepository.findByWasteAccountId(wasteAccountId);
        List<SpecialCollectionDTO> specialCollectionDTOs = specialCollections.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, specialCollectionDTOs);
    }

    @Override
    public ResponseEntityDto createSpecialCollection(SpecialCollectionDTO specialCollectionDTO) {
        try {
            SpecialCollection specialCollection = convertToEntity(specialCollectionDTO);
            WasteAccount wasteAccount = wasteAccountRepository.findById(specialCollectionDTO.getWasteAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException("WasteAccount not found"));

            specialCollection.setWasteAccount(wasteAccount);
            specialCollection.setStatus("Pending");

            SpecialCollection savedSpecialCollection = specialCollectionRepository.save(specialCollection);
            return new ResponseEntityDto(false, convertToDTO(savedSpecialCollection));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntityDto(e.getMessage(), true);
        }
    }

    @Override
    public ResponseEntityDto updateSpecialCollection(Long id, SpecialCollectionDTO specialCollectionDTO) {
        try {
            SpecialCollection specialCollection = specialCollectionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Special collection not found"));

            updateSpecialCollectionFromDTO(specialCollection, specialCollectionDTO);
            SpecialCollection updatedSpecialCollection = specialCollectionRepository.save(specialCollection);
            return new ResponseEntityDto(false, convertToDTO(updatedSpecialCollection));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntityDto(e.getMessage(), true);
        }
    }

    @Override
    public ResponseEntityDto deleteSpecialCollection(Long id) {
        try {
            SpecialCollection specialCollection = specialCollectionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Special collection not found"));
            specialCollectionRepository.delete(specialCollection);
            return new ResponseEntityDto("Special collection deleted successfully", false);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntityDto(e.getMessage(), true);
        }
    }

    private SpecialCollectionDTO convertToDTO(SpecialCollection specialCollection) {
        SpecialCollectionDTO dto = new SpecialCollectionDTO();
        dto.setId(specialCollection.getId());
        dto.setWasteAccountId(specialCollection.getWasteAccount().getId());
        dto.setRequestTime(specialCollection.getRequestTime());
        dto.setCollectionTime(specialCollection.getCollectionTime());
        dto.setStatus(specialCollection.getStatus());
        dto.setWeight(specialCollection.getWeight());
        dto.setFee(specialCollection.getFee());
        return dto;
    }

    private SpecialCollection convertToEntity(SpecialCollectionDTO dto) {
        SpecialCollection specialCollection = new SpecialCollection();
        updateSpecialCollectionFromDTO(specialCollection, dto);
        return specialCollection;
    }

    private void updateSpecialCollectionFromDTO(SpecialCollection specialCollection, SpecialCollectionDTO dto) {
        WasteAccount wasteAccount = wasteAccountRepository.findById(dto.getWasteAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("WasteAccount not found"));
        specialCollection.setWasteAccount(wasteAccount);
        specialCollection.setRequestTime(dto.getRequestTime());
        specialCollection.setCollectionTime(dto.getCollectionTime());
        specialCollection.setStatus(dto.getStatus());
        specialCollection.setWeight(dto.getWeight());
        specialCollection.setFee(dto.getFee());
    }
}