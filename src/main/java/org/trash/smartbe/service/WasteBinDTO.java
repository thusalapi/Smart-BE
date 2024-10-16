package org.trash.smartbe.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WasteBinDTO {
    private Long binId;
    private String wasteType;
    private float maxSize;
    private float currentLevel;
    // Add fields for wasteAccount if needed
}
