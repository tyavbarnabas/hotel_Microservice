package com.codeafrica.userservice.payload;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean Success;
    private String Message;
    private T data;
    private HttpStatus status;

    public ApiResponse(boolean success, String message, HttpStatus status) {
        this.Success = success;
        this.Message = message;
        this.status = status;
    }

    public ApiResponse(boolean success, String message, T data) {
        this.Success = success;
        this.Message = message;
        this.data = data;
    }
}
