package org.trash.smartbe.service;

import org.trash.smartbe.dto.SpecialCollectionDTO;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;

public interface SpecialCollectionService {
    ResponseEntityDto getAllSpecialCollections();
    ResponseEntityDto getSpecialCollectionById(Long id);
    ResponseEntityDto getSpecialCollectionsByWasteAccountId(Long wasteAccountId);
    ResponseEntityDto createSpecialCollection(SpecialCollectionDTO specialCollectionDTO);
    ResponseEntityDto updateSpecialCollection(Long id, SpecialCollectionDTO specialCollectionDTO);
    ResponseEntityDto deleteSpecialCollection(Long id);
}