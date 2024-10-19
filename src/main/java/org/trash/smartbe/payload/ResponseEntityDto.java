package org.trash.smartbe.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntityDto<T> {
    private HttpStatus status;
    private String message;
    private T data;
}