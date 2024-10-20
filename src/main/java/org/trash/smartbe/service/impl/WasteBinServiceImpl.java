package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.WasteBinDTO;
import org.trash.smartbe.model.WasteAccount;
import org.trash.smartbe.model.WasteBin;
import org.trash.smartbe.model.WasteBinHistory;
import org.trash.smartbe.repository.WasteAccountRepository;
import org.trash.smartbe.repository.WasteBinHistoryRepository;
import org.trash.smartbe.repository.WasteBinRepository;
import org.trash.smartbe.service.WasteBinService;
import org.trash.smartbe.util.DTOConverter;

import java.time.LocalDateTime;
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

    @Autowired
    private WasteBinHistoryRepository wasteBinHistoryRepository;


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
    public ResponseEntityDto getWasteBinsByCategory(String wasteCategory) {
        List<WasteBinDTO> wasteBins = wasteBinRepository.findByWasteCategory(wasteCategory).stream()
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
        existingWasteBin.setWasteCategory(wasteBinDTO.getWasteCategory());

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

    @Override
    public ResponseEntityDto increaseWasteLevel(Long id, Double increment) {
        WasteBin wasteBin = wasteBinRepository.findById(id).orElse(null);
        if (wasteBin == null) {
            return new ResponseEntityDto("WasteBin not found", true);
        }
        if (wasteBin.getCurrentLevel() + increment > wasteBin.getCapacity()) {
            return new ResponseEntityDto("Cannot exceed waste bin capacity", true);
        }
        wasteBin.setCurrentLevel(wasteBin.getCurrentLevel() + increment);
        wasteBinRepository.save(wasteBin);
        return new ResponseEntityDto("WasteBin level increased successfully", false);
    }

    @Override
    public ResponseEntityDto collectWaste(Long id) {
        WasteBin wasteBin = wasteBinRepository.findById(id).orElse(null);
        if (wasteBin == null) {
            return new ResponseEntityDto("WasteBin not found", true);
        }

        // Record the current level before resetting
        WasteBinHistory history = new WasteBinHistory();
        history.setWasteBin(wasteBin);
        history.setWasteLevel(wasteBin.getCurrentLevel());
        history.setCollectedAt(LocalDateTime.now());
        history.setWasteCategory(wasteBin.getWasteCategory());

        // Save the history
        wasteBinHistoryRepository.save(history);

        // Reset the current level to 0
        wasteBin.setCurrentLevel(0.0);
        wasteBinRepository.save(wasteBin);

        return new ResponseEntityDto("Waste collected successfully", false);
    }

    @Override
    public ResponseEntityDto getWasteBinHistory(Long id) {
        List<WasteBinHistory> history = wasteBinHistoryRepository.findByWasteBinId(id);
        return new ResponseEntityDto(false, history);
    }

    @Override
    public ResponseEntityDto calculateBill(Long wasteBinId) {
        WasteBin wasteBin = wasteBinRepository.findById(wasteBinId).orElse(null);
        if (wasteBin == null) {
            return new ResponseEntityDto("WasteBin not found", true);
        }

        double billAmount = 0.0;
        if (wasteBin.getIsRecyclable()) {
            billAmount = 0.0; // Bill amount is 0 for recyclable waste
        } else {
            // Implement your own logic for non-recyclable types
        }

        return new ResponseEntityDto(false, billAmount);
    }
}