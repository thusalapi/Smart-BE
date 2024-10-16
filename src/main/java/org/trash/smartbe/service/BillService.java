package org.trash.smartbe.service;

import org.trash.smartbe.dto.BillDTO;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;

public interface BillService {
    ResponseEntityDto getAllBills();
    ResponseEntityDto getBillById(Long id);
    ResponseEntityDto getBillsByWasteAccountId(Long wasteAccountId);
    ResponseEntityDto getBillsByPaymentStatus(boolean isPaid);
    ResponseEntityDto createBill(BillDTO billDTO);
    ResponseEntityDto updateBill(Long id, BillDTO billDTO);
    ResponseEntityDto deleteBill(Long id);
    ResponseEntityDto payBill(Long id);
}