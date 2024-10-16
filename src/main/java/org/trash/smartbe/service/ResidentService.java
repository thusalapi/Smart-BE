// ResidentService.java
package org.trash.smartbe.service;

import org.trash.smartbe.dto.ResidentDTO;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;

public interface ResidentService {
    ResponseEntityDto getAllResidents();
    ResponseEntityDto getResidentById(Long id);
    ResponseEntityDto getResidentByEmail(String email);
    ResponseEntityDto getResidentByWasteAccountId(Long wasteAccountId);
    ResponseEntityDto createResident(ResidentDTO residentDTO);
    ResponseEntityDto updateResident(Long id, ResidentDTO residentDTO);
    ResponseEntityDto deleteResident(Long id);
}