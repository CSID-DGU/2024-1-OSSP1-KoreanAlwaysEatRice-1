package com.example.menuw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    private HttpStatus status;
    private String message;
    private T data;

    private ResponseDto(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static<T> ResponseDto<T> res(final HttpStatus status, final String message) {
        return res(status, message, null);
    }

    public static<T> ResponseDto<T> res(final HttpStatus status, final String message, final T data) {
        return ResponseDto.<T>builder()
                .data(data)
                .status(status)
                .message(message)
                .build();
    }
}
