// WasteBinDTO.java
package org.trash.smartbe.dto;

import lombok.Data;

@Data
public class WasteBinDTO {
    private Long id;
    private String binNumber;
    private Double capacity;
    private Double currentLevel;
    private String wasteType;
    private Long wasteAccountId;
}