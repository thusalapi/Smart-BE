package org.trash.smartbe.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentDTO {
    private Long residentId;
    private String name;
    private String contactInfo;
    // Add fields for wasteAccount if needed
}
