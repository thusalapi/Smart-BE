package org.trash.smartbe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.trash.smartbe.dto.WasteBinDTO;
import org.trash.smartbe.service.WasteBinService;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WasteBinControllerTest {

    @InjectMocks
    private WasteBinController wasteBinController;

    @Mock
    private WasteBinService wasteBinService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllWasteBins_Success() {
        ResponseEntityDto responseDto = new ResponseEntityDto(false, "Waste bins list");
        when(wasteBinService.getAllWasteBins()).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = wasteBinController.getAllWasteBins();

        verify(wasteBinService, times(1)).getAllWasteBins();
        assertEquals("successful", response.getBody().getStatus());
        assertEquals("Waste bins list", response.getBody().getResults().get(0));
    }

    @Test
    public void testGetWasteBinById_Success() {
        WasteBinDTO wasteBinDTO = new WasteBinDTO(); // Fill with necessary fields
        ResponseEntityDto responseDto = new ResponseEntityDto(false, wasteBinDTO);
        when(wasteBinService.getWasteBinById(1L)).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = wasteBinController.getWasteBinById(1L);

        verify(wasteBinService, times(1)).getWasteBinById(1L);
        assertEquals("successful", response.getBody().getStatus());
        assertEquals(wasteBinDTO, response.getBody().getResults().get(0));
    }

    @Test
    public void testGetWasteBinById_NotFound() {
        when(wasteBinService.getWasteBinById(anyLong())).thenReturn(new ResponseEntityDto("WasteBin not found", true));

        ResponseEntity<ResponseEntityDto> response = wasteBinController.getWasteBinById(999L);

        verify(wasteBinService, times(1)).getWasteBinById(999L);
        assertEquals("unsuccessful", response.getBody().getStatus());
        assertEquals("WasteBin not found", ((ResponseEntityDto.Acknowledgement) response.getBody().getResults().get(0)).getMessage());
    }

    @Test
    public void testCreateWasteBin_Success() {
        WasteBinDTO wasteBinDTO = new WasteBinDTO(); // Fill with necessary fields
        ResponseEntityDto responseDto = new ResponseEntityDto(false, wasteBinDTO);
        when(wasteBinService.createWasteBin(any(WasteBinDTO.class))).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = wasteBinController.createWasteBin(wasteBinDTO);

        verify(wasteBinService, times(1)).createWasteBin(wasteBinDTO);
        assertEquals("successful", response.getBody().getStatus());
        assertEquals(wasteBinDTO, response.getBody().getResults().get(0));
    }

    @Test
    public void testUpdateWasteBin_Success() {
        WasteBinDTO wasteBinDTO = new WasteBinDTO(); // Fill with necessary fields
        ResponseEntityDto responseDto = new ResponseEntityDto(false, wasteBinDTO);
        when(wasteBinService.updateWasteBin(anyLong(), any(WasteBinDTO.class))).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = wasteBinController.updateWasteBin(1L, wasteBinDTO);

        verify(wasteBinService, times(1)).updateWasteBin(1L, wasteBinDTO);
        assertEquals("successful", response.getBody().getStatus());
        assertEquals(wasteBinDTO, response.getBody().getResults().get(0));
    }

    @Test
    public void testDeleteWasteBin_Success() {
        ResponseEntityDto responseDto = new ResponseEntityDto("WasteBin deleted successfully", false);
        when(wasteBinService.deleteWasteBin(anyLong())).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = wasteBinController.deleteWasteBin(1L);

        verify(wasteBinService, times(1)).deleteWasteBin(1L);
        assertEquals("successful", response.getBody().getStatus());
        assertEquals("WasteBin deleted successfully", ((ResponseEntityDto.Acknowledgement) response.getBody().getResults().get(0)).getMessage());
    }

    @Test
    public void testDeleteWasteBin_NotFound() {
        when(wasteBinService.deleteWasteBin(anyLong())).thenReturn(new ResponseEntityDto("WasteBin not found", true));

        ResponseEntity<ResponseEntityDto> response = wasteBinController.deleteWasteBin(999L);

        verify(wasteBinService, times(1)).deleteWasteBin(999L);
        assertEquals("unsuccessful", response.getBody().getStatus());
        assertEquals("WasteBin not found", ((ResponseEntityDto.Acknowledgement) response.getBody().getResults().get(0)).getMessage());
    }

    // Additional test cases for remaining endpoints...
}
