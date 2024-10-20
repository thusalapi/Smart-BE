package org.trash.smartbe.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CollectionDTO {
    private Long id;
    private Long wasteBinId;
    private LocalDateTime collectionTime;
    private Double weight;
    private Double fee;
}