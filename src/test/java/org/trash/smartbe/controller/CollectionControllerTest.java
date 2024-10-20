package org.trash.smartbe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.CollectionDTO;
import org.trash.smartbe.service.CollectionService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CollectionControllerTest {

    @InjectMocks
    private CollectionController collectionController;

    @Mock
    private CollectionService collectionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Positive Test: Get all collections
    @Test
    void getAllCollections_Success() {
        ResponseEntityDto responseDto = new ResponseEntityDto(false, Collections.singletonList(new CollectionDTO()));
        when(collectionService.getAllCollections()).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = collectionController.getAllCollections();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("successful", response.getBody().getStatus());
    }

    // Negative Test: Get all collections - Service returns null
    @Test
    void getAllCollections_Failure() {
        when(collectionService.getAllCollections()).thenReturn(null);

        ResponseEntity<ResponseEntityDto> response = collectionController.getAllCollections();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    // Positive Test: Get collection by ID
    @Test
    void getCollectionById_Success() {
        ResponseEntityDto responseDto = new ResponseEntityDto(false, new CollectionDTO());
        when(collectionService.getCollectionById(anyLong())).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = collectionController.getCollectionById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("successful", response.getBody().getStatus());
    }

    // Negative Test: Get collection by ID - ID not found
    @Test
    void getCollectionById_Failure() {
        when(collectionService.getCollectionById(anyLong())).thenReturn(new ResponseEntityDto(true, "Collection not found"));

        ResponseEntity<ResponseEntityDto> response = collectionController.getCollectionById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("unsuccessful", response.getBody().getStatus());
    }

    // Positive Test: Get collections by Waste Bin ID
    @Test
    void getCollectionsByWasteBinId_Success() {
        ResponseEntityDto responseDto = new ResponseEntityDto(false, Collections.singletonList(new CollectionDTO()));
        when(collectionService.getCollectionsByWasteBinId(anyLong())).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = collectionController.getCollectionsByWasteBinId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("successful", response.getBody().getStatus());
    }

    // Negative Test: Get collections by Waste Bin ID - ID not found
    @Test
    void getCollectionsByWasteBinId_Failure() {
        when(collectionService.getCollectionsByWasteBinId(anyLong())).thenReturn(new ResponseEntityDto(true, "Waste Bin not found"));

        ResponseEntity<ResponseEntityDto> response = collectionController.getCollectionsByWasteBinId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("unsuccessful", response.getBody().getStatus());
    }

    // Positive Test: Create new collection
    @Test
    void createCollection_Success() {
        ResponseEntityDto responseDto = new ResponseEntityDto(false, new CollectionDTO());
        when(collectionService.createCollection(any(CollectionDTO.class))).thenReturn(responseDto);

        CollectionDTO collectionDTO = new CollectionDTO();
        ResponseEntity<ResponseEntityDto> response = collectionController.createCollection(collectionDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("successful", response.getBody().getStatus());
    }

    // Negative Test: Create new collection - Invalid data
    @Test
    void createCollection_Failure() {
        when(collectionService.createCollection(any(CollectionDTO.class))).thenReturn(new ResponseEntityDto(true, "Invalid data"));

        CollectionDTO collectionDTO = new CollectionDTO();
        ResponseEntity<ResponseEntityDto> response = collectionController.createCollection(collectionDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("unsuccessful", response.getBody().getStatus());
    }

    // Positive Test: Update collection
    @Test
    void updateCollection_Success() {
        ResponseEntityDto responseDto = new ResponseEntityDto(false, new CollectionDTO());
        when(collectionService.updateCollection(anyLong(), any(CollectionDTO.class))).thenReturn(responseDto);

        CollectionDTO collectionDTO = new CollectionDTO();
        ResponseEntity<ResponseEntityDto> response = collectionController.updateCollection(1L, collectionDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("successful", response.getBody().getStatus());
    }

    // Negative Test: Update collection - Collection not found
    @Test
    void updateCollection_Failure() {
        when(collectionService.updateCollection(anyLong(), any(CollectionDTO.class))).thenReturn(new ResponseEntityDto(true, "Collection not found"));

        CollectionDTO collectionDTO = new CollectionDTO();
        ResponseEntity<ResponseEntityDto> response = collectionController.updateCollection(1L, collectionDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("unsuccessful", response.getBody().getStatus());
    }

    // Positive Test: Delete collection
    @Test
    void deleteCollection_Success() {
        ResponseEntityDto responseDto = new ResponseEntityDto(false, "Collection deleted successfully");
        when(collectionService.deleteCollection(anyLong())).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = collectionController.deleteCollection(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("successful", response.getBody().getStatus());
    }

    // Negative Test: Delete collection - Collection not found
    @Test
    void deleteCollection_Failure() {
        when(collectionService.deleteCollection(anyLong())).thenReturn(new ResponseEntityDto(true, "Collection not found"));

        ResponseEntity<ResponseEntityDto> response = collectionController.deleteCollection(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("unsuccessful", response.getBody().getStatus());
    }
}

