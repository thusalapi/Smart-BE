package org.trash.smartbe.service;

import org.trash.smartbe.dto.CollectionDTO;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;

public interface CollectionService {
    ResponseEntityDto getAllCollections();
    ResponseEntityDto getCollectionById(Long id);
    ResponseEntityDto getCollectionsByWasteBinId(Long wasteBinId);
    ResponseEntityDto createCollection(CollectionDTO collectionDTO);
    ResponseEntityDto updateCollection(Long id, CollectionDTO collectionDTO);
    ResponseEntityDto deleteCollection(Long id);
    double calculateFee(Double weight);
}