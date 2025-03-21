package org.kps.schoolmanagement.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private LocalDateTime timeStamp;
    private String message;
    private HttpStatus httpStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
}
