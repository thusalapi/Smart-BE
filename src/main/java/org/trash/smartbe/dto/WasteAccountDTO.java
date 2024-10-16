package org.trash.smartbe.dto;

import lombok.Data;
import java.util.List;

@Data
public class WasteAccountDTO {
    private Long id;
    private String accountNumber;
    private List<BillDTO> bills;
    private List<WasteBinDTO> wasteBins;
    private ResidentDTO resident;
}