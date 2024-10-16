// BillDTO.java
package org.trash.smartbe.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BillDTO {
    private Long id;
    private LocalDate billDate;
    private BigDecimal amount;
    private boolean isPaid;
    private Long wasteAccountId;

}