package org.trash.smartbe.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SpecialCollectionDTO {
    private Long id;
    private Long wasteAccountId;
    private LocalDateTime requestTime;
    private LocalDateTime collectionTime;
    private String status;
    private Double weight;
    private Double fee;

    // Getters and setters
}