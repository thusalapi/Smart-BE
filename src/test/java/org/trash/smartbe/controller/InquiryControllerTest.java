package org.trash.smartbe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.InquiryDTO;
import org.trash.smartbe.service.InquiryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InquiryControllerTest {

    @Mock
    private InquiryService inquiryService;

    @InjectMocks
    private InquiryController inquiryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Positive Test Cases

    @Test
    public void testCreateInquiry_Success() {
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setSubject("Test subject");
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(false, "Inquiry created successfully");

        when(inquiryService.createInquiry(any(InquiryDTO.class))).thenReturn(responseEntityDto);

        ResponseEntityDto result = inquiryController.createInquiry(inquiryDTO);

        assertEquals("successful", result.getStatus());
        verify(inquiryService, times(1)).createInquiry(any(InquiryDTO.class));
    }

    @Test
    public void testGetInquiryById_Success() {
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(false, "Inquiry found");
        Long inquiryId = 1L;

        when(inquiryService.getInquiryById(inquiryId)).thenReturn(responseEntityDto);

        ResponseEntityDto result = inquiryController.getInquiryById(inquiryId);

        assertEquals("successful", result.getStatus());
        verify(inquiryService, times(1)).getInquiryById(inquiryId);
    }

    @Test
    public void testGetAllInquiries_Success() {
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(false, "All inquiries fetched");

        when(inquiryService.getAllInquiries()).thenReturn(responseEntityDto);

        ResponseEntityDto result = inquiryController.getAllInquiries();

        assertEquals("successful", result.getStatus());
        verify(inquiryService, times(1)).getAllInquiries();
    }

    @Test
    public void testUpdateInquiry_Success() {
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setSubject("Updated subject");
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(false, "Inquiry updated successfully");
        Long inquiryId = 1L;

        when(inquiryService.updateInquiry(inquiryId, inquiryDTO)).thenReturn(responseEntityDto);

        ResponseEntityDto result = inquiryController.updateInquiry(inquiryId, inquiryDTO);

        assertEquals("successful", result.getStatus());
        verify(inquiryService, times(1)).updateInquiry(inquiryId, inquiryDTO);
    }

    @Test
    public void testDeleteInquiry_Success() {
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(false, "Inquiry deleted successfully");
        Long inquiryId = 1L;

        when(inquiryService.deleteInquiry(inquiryId)).thenReturn(responseEntityDto);

        ResponseEntityDto result = inquiryController.deleteInquiry(inquiryId);

        assertEquals("successful", result.getStatus());
        verify(inquiryService, times(1)).deleteInquiry(inquiryId);
    }

    // Negative Test Cases

    @Test
    public void testCreateInquiry_Failure() {
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setSubject("Test subject");
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(true, "Inquiry creation failed");

        when(inquiryService.createInquiry(any(InquiryDTO.class))).thenReturn(responseEntityDto);

        ResponseEntityDto result = inquiryController.createInquiry(inquiryDTO);

        assertEquals("unsuccessful", result.getStatus());
        verify(inquiryService, times(1)).createInquiry(any(InquiryDTO.class));
    }

    @Test
    public void testGetInquiryById_NotFound() {
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(true, "Inquiry not found");
        Long inquiryId = 1L;

        when(inquiryService.getInquiryById(inquiryId)).thenReturn(responseEntityDto);

        ResponseEntityDto result = inquiryController.getInquiryById(inquiryId);

        assertEquals("unsuccessful", result.getStatus());
        verify(inquiryService, times(1)).getInquiryById(inquiryId);
    }

    @Test
    public void testGetAllInquiries_Failure() {
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(true, "Failed to fetch inquiries");

        when(inquiryService.getAllInquiries()).thenReturn(responseEntityDto);

        ResponseEntityDto result = inquiryController.getAllInquiries();

        assertEquals("unsuccessful", result.getStatus());
        verify(inquiryService, times(1)).getAllInquiries();
    }

    @Test
    public void testUpdateInquiry_Failure() {
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setSubject("Updated subject");
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(true, "Inquiry update failed");
        Long inquiryId = 1L;

        when(inquiryService.updateInquiry(inquiryId, inquiryDTO)).thenReturn(responseEntityDto);

        ResponseEntityDto result = inquiryController.updateInquiry(inquiryId, inquiryDTO);

        assertEquals("unsuccessful", result.getStatus());
        verify(inquiryService, times(1)).updateInquiry(inquiryId, inquiryDTO);
    }

    @Test
    public void testDeleteInquiry_Failure() {
        ResponseEntityDto responseEntityDto = new ResponseEntityDto(true, "Inquiry deletion failed");
        Long inquiryId = 1L;

        when(inquiryService.deleteInquiry(inquiryId)).thenReturn(responseEntityDto);

        ResponseEntityDto result = inquiryController.deleteInquiry(inquiryId);

        assertEquals("unsuccessful", result.getStatus());
        verify(inquiryService, times(1)).deleteInquiry(inquiryId);
    }
}

