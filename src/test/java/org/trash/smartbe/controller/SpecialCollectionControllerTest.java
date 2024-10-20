package org.trash.smartbe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.SpecialCollectionDTO;
import org.trash.smartbe.service.SpecialCollectionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class SpecialCollectionControllerTest {

    @Mock
    private SpecialCollectionService specialCollectionService;

    @InjectMocks
    private SpecialCollectionController specialCollectionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Positive Test Cases

    @Test
    public void testGetAllSpecialCollections_Success() {
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(false, "All special collections fetched");

        when(specialCollectionService.getAllSpecialCollections()).thenReturn(responseEntityDto);

        ResponseEntity<ResponseEntityDto> response = specialCollectionController.getAllSpecialCollections();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("successful", response.getBody().getStatus());
        verify(specialCollectionService, times(1)).getAllSpecialCollections();
    }

    @Test
    public void testGetSpecialCollectionById_Success() {
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(false, "Special collection found");
        Long specialCollectionId = 1L;

        when(specialCollectionService.getSpecialCollectionById(specialCollectionId)).thenReturn(responseEntityDto);

        ResponseEntity<ResponseEntityDto> response = specialCollectionController.getSpecialCollectionById(specialCollectionId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("successful", response.getBody().getStatus());
        verify(specialCollectionService, times(1)).getSpecialCollectionById(specialCollectionId);
    }

    @Test
    public void testCreateSpecialCollection_Success() {
        SpecialCollectionDTO specialCollectionDTO = new SpecialCollectionDTO();
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(false, "Special collection created");

        when(specialCollectionService.createSpecialCollection(any(SpecialCollectionDTO.class))).thenReturn(responseEntityDto);

        ResponseEntity<ResponseEntityDto> response = specialCollectionController.createSpecialCollection(specialCollectionDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("successful", response.getBody().getStatus());
        verify(specialCollectionService, times(1)).createSpecialCollection(any(SpecialCollectionDTO.class));
    }

    @Test
    public void testUpdateSpecialCollection_Success() {
        SpecialCollectionDTO specialCollectionDTO = new SpecialCollectionDTO();
        Long specialCollectionId = 1L;
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(false, "Special collection updated");

        when(specialCollectionService.updateSpecialCollection(specialCollectionId, specialCollectionDTO)).thenReturn(responseEntityDto);

        ResponseEntity<ResponseEntityDto> response = specialCollectionController.updateSpecialCollection(specialCollectionId, specialCollectionDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("successful", response.getBody().getStatus());
        verify(specialCollectionService, times(1)).updateSpecialCollection(specialCollectionId, specialCollectionDTO);
    }

    @Test
    public void testDeleteSpecialCollection_Success() {
        Long specialCollectionId = 1L;
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(false, "Special collection deleted");

        when(specialCollectionService.deleteSpecialCollection(specialCollectionId)).thenReturn(responseEntityDto);

        ResponseEntity<ResponseEntityDto> response = specialCollectionController.deleteSpecialCollection(specialCollectionId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("successful", response.getBody().getStatus());
        verify(specialCollectionService, times(1)).deleteSpecialCollection(specialCollectionId);
    }

    // Negative Test Cases

    @Test
    public void testGetAllSpecialCollections_Failure() {
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(true, "Failed to fetch special collections");

        when(specialCollectionService.getAllSpecialCollections()).thenReturn(responseEntityDto);

        ResponseEntity<ResponseEntityDto> response = specialCollectionController.getAllSpecialCollections();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("unsuccessful", response.getBody().getStatus());
        verify(specialCollectionService, times(1)).getAllSpecialCollections();
    }

    @Test
    public void testGetSpecialCollectionById_NotFound() {
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(true, "Special collection not found");
        Long specialCollectionId = 1L;

        when(specialCollectionService.getSpecialCollectionById(specialCollectionId)).thenReturn(responseEntityDto);

        ResponseEntity<ResponseEntityDto> response = specialCollectionController.getSpecialCollectionById(specialCollectionId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("unsuccessful", response.getBody().getStatus());
        verify(specialCollectionService, times(1)).getSpecialCollectionById(specialCollectionId);
    }

    @Test
    public void testCreateSpecialCollection_Failure() {
        SpecialCollectionDTO specialCollectionDTO = new SpecialCollectionDTO();
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(true, "Failed to create special collection");

        when(specialCollectionService.createSpecialCollection(any(SpecialCollectionDTO.class))).thenReturn(responseEntityDto);

        ResponseEntity<ResponseEntityDto> response = specialCollectionController.createSpecialCollection(specialCollectionDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("unsuccessful", response.getBody().getStatus());
        verify(specialCollectionService, times(1)).createSpecialCollection(any(SpecialCollectionDTO.class));
    }

    @Test
    public void testUpdateSpecialCollection_Failure() {
        SpecialCollectionDTO specialCollectionDTO = new SpecialCollectionDTO();
        Long specialCollectionId = 1L;
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(true, "Failed to update special collection");

        when(specialCollectionService.updateSpecialCollection(specialCollectionId, specialCollectionDTO)).thenReturn(responseEntityDto);

        ResponseEntity<ResponseEntityDto> response = specialCollectionController.updateSpecialCollection(specialCollectionId, specialCollectionDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("unsuccessful", response.getBody().getStatus());
        verify(specialCollectionService, times(1)).updateSpecialCollection(specialCollectionId, specialCollectionDTO);
    }

    @Test
    public void testDeleteSpecialCollection_Failure() {
        Long specialCollectionId = 1L;
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(true, "Failed to delete special collection");

        when(specialCollectionService.deleteSpecialCollection(specialCollectionId)).thenReturn(responseEntityDto);

        ResponseEntity<ResponseEntityDto> response = specialCollectionController.deleteSpecialCollection(specialCollectionId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("unsuccessful", response.getBody().getStatus());
        verify(specialCollectionService, times(1)).deleteSpecialCollection(specialCollectionId);
    }
}

