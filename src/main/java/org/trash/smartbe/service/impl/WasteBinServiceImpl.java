// WasteBinServiceImpl.java
package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.dto.WasteBinDTO;
import org.trash.smartbe.model.WasteBin;
import org.trash.smartbe.model.WasteAccount;
import org.trash.smartbe.repository.WasteBinRepository;
import org.trash.smartbe.repository.WasteAccountRepository;
import org.trash.smartbe.service.WasteBinService;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.util.DTOConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WasteBinServiceImpl implements WasteBinService {

    @Autowired
    private WasteBinRepository wasteBinRepository;

    @Autowired
    private WasteAccountRepository wasteAccountRepository;

    @Autowired
    private DTOConverter dtoConverter;

    @Override
    public ResponseEntityDto getAllWasteBins() {
        List<WasteBinDTO> wasteBins = wasteBinRepository.findAll().stream()
                .map(dtoConverter::convertToWasteBinDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, wasteBins);
    }

    @Override
    public ResponseEntityDto getWasteBinById(Long id) {
        WasteBin wasteBin = wasteBinRepository.findById(id).orElse(null);
        if (wasteBin == null) {
            return new ResponseEntityDto("WasteBin not found", true);
        }
        return new ResponseEntityDto(false, dtoConverter.convertToWasteBinDTO(wasteBin));
    }

    @Override
    public ResponseEntityDto getWasteBinByBinNumber(String binNumber) {
        WasteBin wasteBin = wasteBinRepository.findByBinNumber(binNumber);
        if (wasteBin == null) {
            return new ResponseEntityDto("WasteBin not found", true);
        }
        return new ResponseEntityDto(false, dtoConverter.convertToWasteBinDTO(wasteBin));
    }

    @Override
    public ResponseEntityDto getWasteBinsByWasteAccountId(Long wasteAccountId) {
        List<WasteBinDTO> wasteBins = wasteBinRepository.findByWasteAccountId(wasteAccountId).stream()
                .map(dtoConverter::convertToWasteBinDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, wasteBins);
    }

    @Override
    public ResponseEntityDto getWasteBinsByWasteType(String wasteType) {
        List<WasteBinDTO> wasteBins = wasteBinRepository.findByWasteType(wasteType).stream()
                .map(dtoConverter::convertToWasteBinDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, wasteBins);
    }

    @Override
    public ResponseEntityDto createWasteBin(WasteBinDTO wasteBinDTO) {
        WasteAccount wasteAccount = wasteAccountRepository.findById(wasteBinDTO.getWasteAccountId())
                .orElse(null);
        if (wasteAccount == null) {
            return new ResponseEntityDto("WasteAccount not found", true);
        }

        WasteBin wasteBin = dtoConverter.convertToWasteBin(wasteBinDTO);
        wasteBin.setWasteAccount(wasteAccount);
        WasteBin savedWasteBin = wasteBinRepository.save(wasteBin);
        return new ResponseEntityDto(false, dtoConverter.convertToWasteBinDTO(savedWasteBin));
    }

    @Override
    public ResponseEntityDto updateWasteBin(Long id, WasteBinDTO wasteBinDTO) {
        WasteBin existingWasteBin = wasteBinRepository.findById(id).orElse(null);
        if (existingWasteBin == null) {
            return new ResponseEntityDto("WasteBin not found", true);
        }

        existingWasteBin.setBinNumber(wasteBinDTO.getBinNumber());
        existingWasteBin.setCapacity(wasteBinDTO.getCapacity());
        existingWasteBin.setCurrentLevel(wasteBinDTO.getCurrentLevel());
        existingWasteBin.setWasteType(wasteBinDTO.getWasteType());

        if (!existingWasteBin.getWasteAccount().getId().equals(wasteBinDTO.getWasteAccountId())) {
            WasteAccount newWasteAccount = wasteAccountRepository.findById(wasteBinDTO.getWasteAccountId())
                    .orElse(null);
            if (newWasteAccount == null) {
                return new ResponseEntityDto("New WasteAccount not found", true);
            }
            existingWasteBin.setWasteAccount(newWasteAccount);
        }

        WasteBin updatedWasteBin = wasteBinRepository.save(existingWasteBin);
        return new ResponseEntityDto(false, dtoConverter.convertToWasteBinDTO(updatedWasteBin));
    }

    @Override
    public ResponseEntityDto deleteWasteBin(Long id) {
        if (!wasteBinRepository.existsById(id)) {
            return new ResponseEntityDto("WasteBin not found", true);
        }
        wasteBinRepository.deleteById(id);
        return new ResponseEntityDto("WasteBin deleted successfully", false);
    }

    @Override
    public ResponseEntityDto updateWasteBinLevel(Long id, Double newLevel) {
        WasteBin wasteBin = wasteBinRepository.findById(id).orElse(null);
        if (wasteBin == null) {
            return new ResponseEntityDto("WasteBin not found", true);
        }
        if (newLevel < 0 || newLevel > wasteBin.getCapacity()) {
            return new ResponseEntityDto("Invalid waste level", true);
        }
        wasteBin.setCurrentLevel(newLevel);
        wasteBinRepository.save(wasteBin);
        return new ResponseEntityDto("WasteBin level updated successfully", false);
    }
}