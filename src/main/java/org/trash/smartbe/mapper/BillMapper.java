package org.trash.smartbe.mapper;

import org.trash.smartbe.model.Bill;
import org.trash.smartbe.service.BillDTO;

public class BillMapper {

    public static BillDTO toDTO(Bill bill) {
        BillDTO dto = new BillDTO();
        dto.setBillId(bill.getBillId());
        dto.setIssueDate(bill.getIssueDate());
        dto.setAmount(bill.getAmount());
        dto.setWasteAccountId(bill.getWasteAccount() != null ? bill.getWasteAccount().getAccountId() : null);
        return dto;
    }

    public static Bill toEntity(BillDTO dto) {
        Bill bill = new Bill();
        bill.setBillId(dto.getBillId());
        bill.setIssueDate(dto.getIssueDate());
        bill.setAmount(dto.getAmount());
        // If you need to set the WasteAccount entity, retrieve it from the database
        return bill;
    }
}
