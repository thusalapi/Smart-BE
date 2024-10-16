package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.dto.BillDTO;
import org.trash.smartbe.model.Bill;
import org.trash.smartbe.model.WasteAccount;
import org.trash.smartbe.repository.BillRepository;
import org.trash.smartbe.repository.WasteAccountRepository;
import org.trash.smartbe.service.BillService;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.util.DTOConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private WasteAccountRepository wasteAccountRepository;

    @Autowired
    private DTOConverter dtoConverter;

    @Override
    public ResponseEntityDto getAllBills() {
        List<BillDTO> bills = billRepository.findAll().stream()
                .map(dtoConverter::convertToBillDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, bills);
    }

    @Override
    public ResponseEntityDto getBillById(Long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if (bill == null) {
            return new ResponseEntityDto("Bill not found", true);
        }
        return new ResponseEntityDto(false, dtoConverter.convertToBillDTO(bill));
    }

    @Override
    public ResponseEntityDto getBillsByWasteAccountId(Long wasteAccountId) {
        List<BillDTO> bills = billRepository.findByWasteAccountId(wasteAccountId).stream()
                .map(dtoConverter::convertToBillDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, bills);
    }

    @Override
    public ResponseEntityDto getBillsByPaymentStatus(boolean isPaid) {
        List<BillDTO> bills = billRepository.findByIsPaid(isPaid).stream()
                .map(dtoConverter::convertToBillDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, bills);
    }

    @Override
    public ResponseEntityDto createBill(BillDTO billDTO) {
        WasteAccount wasteAccount = wasteAccountRepository.findById(billDTO.getWasteAccountId())
                .orElse(null);
        if (wasteAccount == null) {
            return new ResponseEntityDto("WasteAccount not found", true);
        }

        Bill bill = dtoConverter.convertToBill(billDTO);
        bill.setWasteAccount(wasteAccount);
        Bill savedBill = billRepository.save(bill);
        return new ResponseEntityDto(false, dtoConverter.convertToBillDTO(savedBill));
    }

    @Override
    public ResponseEntityDto updateBill(Long id, BillDTO billDTO) {
        Bill existingBill = billRepository.findById(id).orElse(null);
        if (existingBill == null) {
            return new ResponseEntityDto("Bill not found", true);
        }

        existingBill.setBillDate(billDTO.getBillDate());
        existingBill.setAmount(billDTO.getAmount());
        existingBill.setPaid(billDTO.isPaid());

        if (!existingBill.getWasteAccount().getId().equals(billDTO.getWasteAccountId())) {
            WasteAccount newWasteAccount = wasteAccountRepository.findById(billDTO.getWasteAccountId())
                    .orElse(null);
            if (newWasteAccount == null) {
                return new ResponseEntityDto("New WasteAccount not found", true);
            }
            existingBill.setWasteAccount(newWasteAccount);
        }

        Bill updatedBill = billRepository.save(existingBill);
        return new ResponseEntityDto(false, dtoConverter.convertToBillDTO(updatedBill));
    }

    @Override
    public ResponseEntityDto deleteBill(Long id) {
        if (!billRepository.existsById(id)) {
            return new ResponseEntityDto("Bill not found", true);
        }
        billRepository.deleteById(id);
        return new ResponseEntityDto("Bill deleted successfully", false);
    }

    @Override
    public ResponseEntityDto payBill(Long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if (bill == null) {
            return new ResponseEntityDto("Bill not found", true);
        }
        if (bill.isPaid()) {
            return new ResponseEntityDto("Bill is already paid", true);
        }
        bill.setPaid(true);
        billRepository.save(bill);
        return new ResponseEntityDto("Bill paid successfully", false);
    }
}