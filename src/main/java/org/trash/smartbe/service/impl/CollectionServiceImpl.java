package org.trash.smartbe.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.dto.CollectionDTO;
import org.trash.smartbe.model.Collection;
import org.trash.smartbe.model.WasteBin;
import org.trash.smartbe.repository.CollectionRepository;
import org.trash.smartbe.repository.WasteBinRepository;
import org.trash.smartbe.service.CollectionService;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private WasteBinRepository wasteBinRepository;

    @Override
    public ResponseEntityDto getAllCollections() {
        List<Collection> collections = collectionRepository.findAll();
        List<CollectionDTO> collectionDTOs = collections.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, collectionDTOs);
    }

    @Override
    public ResponseEntityDto getCollectionById(Long id) {
        try {
            Collection collection = collectionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Collection not found"));
            return new ResponseEntityDto(false, convertToDTO(collection));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntityDto(e.getMessage(), true);
        }
    }

    @Override
    public ResponseEntityDto getCollectionsByWasteBinId(Long wasteBinId) {
        List<Collection> collections = collectionRepository.findByWasteBinId(wasteBinId);
        List<CollectionDTO> collectionDTOs = collections.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, collectionDTOs);
    }

    @Override
    @Transactional
    public ResponseEntityDto createCollection(CollectionDTO collectionDTO) {
        WasteBin wasteBin = wasteBinRepository.findById(collectionDTO.getWasteBinId())
                .orElseThrow(() -> new ResourceNotFoundException("WasteBin not found"));

        // Create a new Collection entity from DTO
        Collection collection = convertToEntity(collectionDTO);

        // Update waste bin level to zero
        wasteBin.setCurrentLevel(0.0);
        wasteBinRepository.save(wasteBin);

        // Calculate fee based on weight
        double fee = calculateFee(collectionDTO.getWeight());
        collection.setFee(fee);
        collection.setWasteBin(wasteBin); // Associate the WasteBin

        Collection savedCollection = collectionRepository.save(collection);
        return new ResponseEntityDto(false, convertToDTO(savedCollection));
    }

    @Override
    public ResponseEntityDto updateCollection(Long id, CollectionDTO collectionDTO) {
        try {
            Collection collection = collectionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Collection not found"));

            updateCollectionFromDTO(collection, collectionDTO);
            Collection updatedCollection = collectionRepository.save(collection);
            return new ResponseEntityDto(false, convertToDTO(updatedCollection));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntityDto(e.getMessage(), true);
        }
    }

    @Override
    public ResponseEntityDto deleteCollection(Long id) {
        try {
            Collection collection = collectionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Collection not found"));
            collectionRepository.delete(collection);
            return new ResponseEntityDto("Collection deleted successfully", false);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntityDto(e.getMessage(), true);
        }
    }

    private CollectionDTO convertToDTO(Collection collection) {
        CollectionDTO dto = new CollectionDTO();
        dto.setId(collection.getId());
        dto.setWasteBinId(collection.getWasteBin().getId());
        dto.setCollectionTime(collection.getCollectionTime());
        dto.setWeight(collection.getWeight());
        dto.setFee(collection.getFee());
        return dto;
    }

    private Collection convertToEntity(CollectionDTO dto) {
        Collection collection = new Collection();
        updateCollectionFromDTO(collection, dto);
        return collection;
    }

    private void updateCollectionFromDTO(Collection collection, CollectionDTO dto) {
        WasteBin wasteBin = wasteBinRepository.findById(dto.getWasteBinId())
                .orElseThrow(() -> new ResourceNotFoundException("WasteBin not found"));
        collection.setWasteBin(wasteBin);
        collection.setCollectionTime(dto.getCollectionTime());
        collection.setWeight(dto.getWeight());
        collection.setFee(dto.getFee());
    }

    private double calculateFee(Double weight) {
        // Implement fee calculation logic based on weight
        return weight * 0.5;
    }
}