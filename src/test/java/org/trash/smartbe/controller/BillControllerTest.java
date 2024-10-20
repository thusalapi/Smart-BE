package org.trash.smartbe.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.BillDTO;
import org.trash.smartbe.service.BillService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

public class BillControllerTest {

    @InjectMocks
    private BillController billController;

    @Mock
    private BillService billService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test cases go here

    @Test
    public void testGetAllBills_Success() {
        ResponseEntityDto responseDto = new ResponseEntityDto();
        when(billService.getAllBills()).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = billController.getAllBills();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    public void testGetBillById_Success() {
        Long billId = 1L;
        ResponseEntityDto responseDto = new ResponseEntityDto();
        when(billService.getBillById(billId)).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = billController.getBillById(billId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    public void testCreateBill_Success() {
        BillDTO billDTO = new BillDTO();
        ResponseEntityDto responseDto = new ResponseEntityDto();
        when(billService.createBill(billDTO)).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = billController.createBill(billDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    public void testUpdateBill_Success() {
        Long billId = 1L;
        BillDTO billDTO = new BillDTO();
        ResponseEntityDto responseDto = new ResponseEntityDto();
        when(billService.updateBill(billId, billDTO)).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = billController.updateBill(billId, billDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    public void testDeleteBill_Success() {
        Long billId = 1L;
        ResponseEntityDto responseDto = new ResponseEntityDto();
        when(billService.deleteBill(billId)).thenReturn(responseDto);

        ResponseEntity<ResponseEntityDto> response = billController.deleteBill(billId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    public void testGetBillById_NotFound() {
        Long billId = 1L;
        when(billService.getBillById(billId)).thenThrow(new IllegalArgumentException("Bill not found"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            billController.getBillById(billId);
        });

        assertEquals("Bill not found", exception.getMessage());
    }

    @Test
    public void testCreateBill_InvalidInput() {
        BillDTO billDTO = new BillDTO();  // Invalid because required fields are null
        when(billService.createBill(billDTO)).thenThrow(new IllegalArgumentException("Invalid input"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            billController.createBill(billDTO);
        });

        assertEquals("Invalid input", exception.getMessage());
    }

    @Test
    public void testUpdateBill_BillNotFound() {
        Long billId = 1L;
        BillDTO billDTO = new BillDTO();
        when(billService.updateBill(billId, billDTO)).thenThrow(new IllegalArgumentException("Bill not found"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            billController.updateBill(billId, billDTO);
        });

        assertEquals("Bill not found", exception.getMessage());
    }

    @Test
    public void testDeleteBill_BillNotFound() {
        Long billId = 1L;
        when(billService.deleteBill(billId)).thenThrow(new IllegalArgumentException("Bill not found"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            billController.deleteBill(billId);
        });

        assertEquals("Bill not found", exception.getMessage());
    }

    @Test
    public void testPayBill_AlreadyPaid() {
        Long billId = 1L;
        when(billService.payBill(billId)).thenThrow(new IllegalStateException("Bill already paid"));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            billController.payBill(billId);
        });

        assertEquals("Bill already paid", exception.getMessage());
    }

}

