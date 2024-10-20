// DTOConverter.java
package org.trash.smartbe.util;

import org.springframework.stereotype.Component;
import org.trash.smartbe.dto.*;
import org.trash.smartbe.model.*;

@Component
public class DTOConverter {

    public WasteAccountDTO convertToWasteAccountDTO(WasteAccount wasteAccount) {
        WasteAccountDTO dto = new WasteAccountDTO();
        dto.setId(wasteAccount.getId());
        dto.setAccountNumber(wasteAccount.getAccountNumber());
        // Set other fields as needed
        return dto;
    }

    public WasteAccount convertToWasteAccount(WasteAccountDTO dto) {
        WasteAccount wasteAccount = new WasteAccount();
        wasteAccount.setId(dto.getId());
        wasteAccount.setAccountNumber(dto.getAccountNumber());
        // Set other fields as needed
        return wasteAccount;
    }

    public BillDTO convertToBillDTO(Bill bill) {
        BillDTO dto = new BillDTO();
        dto.setId(bill.getId());
        dto.setBillDate(bill.getBillDate());
        dto.setAmount(bill.getAmount());
        dto.setPaid(bill.isPaid());
        dto.setWasteAccountId(bill.getWasteAccount().getId());
        return dto;
    }

    public Bill convertToBill(BillDTO dto) {
        Bill bill = new Bill();
        bill.setId(dto.getId());
        bill.setBillDate(dto.getBillDate());
        bill.setAmount(dto.getAmount());
        bill.setPaid(dto.isPaid());
        // WasteAccount should be set separately
        return bill;
    }

    public WasteBinDTO convertToWasteBinDTO(WasteBin wasteBin) {
        WasteBinDTO dto = new WasteBinDTO();
        dto.setId(wasteBin.getId());
        dto.setBinNumber(wasteBin.getBinNumber());
        dto.setCapacity(wasteBin.getCapacity());
        dto.setCurrentLevel(wasteBin.getCurrentLevel());
        dto.setCurrentLevel(wasteBin.getCurrentLevel());
        dto.setWasteCategory(wasteBin.getWasteCategory());
        dto.setIsRecyclable(wasteBin.getIsRecyclable());
        dto.setWasteAccountId(wasteBin.getWasteAccount().getId());
        return dto;
    }

    public WasteBin convertToWasteBin(WasteBinDTO dto) {
        WasteBin wasteBin = new WasteBin();
        wasteBin.setId(dto.getId());
        wasteBin.setBinNumber(dto.getBinNumber());
        wasteBin.setCapacity(dto.getCapacity());
        wasteBin.setCurrentLevel(dto.getCurrentLevel());
        wasteBin.setIsRecyclable(dto.getIsRecyclable());
        dto.setWasteCategory(wasteBin.getWasteCategory());
        return wasteBin;
    }

    public ResidentDTO convertToResidentDTO(Resident resident) {
        ResidentDTO dto = new ResidentDTO();
        dto.setId(resident.getId());
        dto.setFirstName(resident.getFirstName());
        dto.setLastName(resident.getLastName());
        dto.setEmail(resident.getEmail());
        dto.setPhone(resident.getPhone());
        dto.setAddress(resident.getAddress());
        if (resident.getWasteAccount() != null) {
            dto.setWasteAccountId(resident.getWasteAccount().getId());
        }
        return dto;
    }

    public Resident convertToResident(ResidentDTO dto) {
        Resident resident = new Resident();
        resident.setId(dto.getId());
        resident.setFirstName(dto.getFirstName());
        resident.setLastName(dto.getLastName());
        resident.setEmail(dto.getEmail());
        resident.setPhone(dto.getPhone());
        resident.setAddress(dto.getAddress());
        // WasteAccount should be set separately
        return resident;
    }

    // Add other conversion methods as needed for Resident, etc.
}