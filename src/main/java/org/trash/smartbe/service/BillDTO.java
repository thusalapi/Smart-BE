package org.trash.smartbe.service;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BillDTO {
    private Long billId;
    private Date issueDate;
    private float amount;
    private Long wasteAccountId; // Assuming you want to include the WasteAccount ID
}
