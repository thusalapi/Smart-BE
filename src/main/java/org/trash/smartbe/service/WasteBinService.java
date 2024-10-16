// WasteBinService.java
package org.trash.smartbe.service;

import org.trash.smartbe.dto.WasteBinDTO;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;

public interface WasteBinService {
    ResponseEntityDto getAllWasteBins();
    ResponseEntityDto getWasteBinById(Long id);
    ResponseEntityDto getWasteBinByBinNumber(String binNumber);
    ResponseEntityDto getWasteBinsByWasteAccountId(Long wasteAccountId);
    ResponseEntityDto getWasteBinsByWasteType(String wasteType);
    ResponseEntityDto createWasteBin(WasteBinDTO wasteBinDTO);
    ResponseEntityDto updateWasteBin(Long id, WasteBinDTO wasteBinDTO);
    ResponseEntityDto deleteWasteBin(Long id);
    ResponseEntityDto updateWasteBinLevel(Long id, Double newLevel);
}