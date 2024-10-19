package org.trash.smartbe.dto;

import lombok.Data;

@Data
public class InquiryDTO {
    private Long id;
    private String subject;
    private String message;
    private String createdAt;
    private Long userId;
}