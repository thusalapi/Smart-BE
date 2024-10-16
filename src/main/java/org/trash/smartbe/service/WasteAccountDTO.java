package org.trash.smartbe.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WasteAccountDTO {
    private Long accountId;
    private String address;
    // Add fields for resident, bills, wasteBins if needed
}
