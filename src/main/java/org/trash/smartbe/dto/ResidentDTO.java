// ResidentDTO.java
package org.trash.smartbe.dto;

import lombok.Data;

@Data
public class ResidentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Long wasteAccountId;
}