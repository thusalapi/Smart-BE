// WasteAccountService.java
package org.trash.smartbe.service;

import org.trash.smartbe.dto.WasteAccountDTO;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;

public interface WasteAccountService {
    ResponseEntityDto getAllWasteAccounts();
    ResponseEntityDto getWasteAccountById(Long id);
    ResponseEntityDto getWasteAccountByAccountNumber(String accountNumber);
    ResponseEntityDto createWasteAccount(WasteAccountDTO wasteAccountDTO);
    ResponseEntityDto updateWasteAccount(Long id, WasteAccountDTO wasteAccountDTO);
    ResponseEntityDto deleteWasteAccount(Long id);
}