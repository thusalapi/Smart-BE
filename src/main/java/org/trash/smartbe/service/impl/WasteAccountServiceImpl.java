// WasteAccountServiceImpl.java
package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.dto.WasteAccountDTO;
import org.trash.smartbe.model.WasteAccount;
import org.trash.smartbe.repository.WasteAccountRepository;
import org.trash.smartbe.service.WasteAccountService;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.util.DTOConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WasteAccountServiceImpl implements WasteAccountService {

    @Autowired
    private WasteAccountRepository wasteAccountRepository;

    @Autowired
    private DTOConverter dtoConverter;

    @Override
    public ResponseEntityDto getAllWasteAccounts() {
        List<WasteAccountDTO> wasteAccounts = wasteAccountRepository.findAll().stream()
                .map(dtoConverter::convertToWasteAccountDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, wasteAccounts);
    }

    @Override
    public ResponseEntityDto getWasteAccountById(Long id) {
        WasteAccount wasteAccount = wasteAccountRepository.findById(id)
                .orElse(null);
        if (wasteAccount == null) {
            return new ResponseEntityDto("WasteAccount not found", true);
        }
        return new ResponseEntityDto(false, dtoConverter.convertToWasteAccountDTO(wasteAccount));
    }

    @Override
    public ResponseEntityDto getWasteAccountByAccountNumber(String accountNumber) {
        WasteAccount wasteAccount = wasteAccountRepository.findByAccountNumber(accountNumber);
        if (wasteAccount == null) {
            return new ResponseEntityDto("WasteAccount not found", true);
        }
        return new ResponseEntityDto(false, dtoConverter.convertToWasteAccountDTO(wasteAccount));
    }

    @Override
    public ResponseEntityDto createWasteAccount(WasteAccountDTO wasteAccountDTO) {
        WasteAccount wasteAccount = dtoConverter.convertToWasteAccount(wasteAccountDTO);
        WasteAccount savedWasteAccount = wasteAccountRepository.save(wasteAccount);
        return new ResponseEntityDto(false, dtoConverter.convertToWasteAccountDTO(savedWasteAccount));
    }

    @Override
    public ResponseEntityDto updateWasteAccount(Long id, WasteAccountDTO wasteAccountDTO) {
        WasteAccount existingWasteAccount = wasteAccountRepository.findById(id)
                .orElse(null);
        if (existingWasteAccount == null) {
            return new ResponseEntityDto("WasteAccount not found", true);
        }

        // Update fields
        existingWasteAccount.setAccountNumber(wasteAccountDTO.getAccountNumber());
        // Update other fields as necessary

        WasteAccount updatedWasteAccount = wasteAccountRepository.save(existingWasteAccount);
        return new ResponseEntityDto(false, dtoConverter.convertToWasteAccountDTO(updatedWasteAccount));
    }

    @Override
    public ResponseEntityDto deleteWasteAccount(Long id) {
        if (!wasteAccountRepository.existsById(id)) {
            return new ResponseEntityDto("WasteAccount not found", true);
        }
        wasteAccountRepository.deleteById(id);
        return new ResponseEntityDto("WasteAccount deleted successfully", false);
    }
}