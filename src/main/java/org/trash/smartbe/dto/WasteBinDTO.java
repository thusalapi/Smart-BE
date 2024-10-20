package org.trash.smartbe.dto;

import lombok.Data;

@Data
public class WasteBinDTO {
    private Long id;
    private String binNumber;
    private Double capacity;
    private Double currentLevel;
    private String wasteCategory;
    private Boolean isRecyclable;
    private Long wasteAccountId;
}