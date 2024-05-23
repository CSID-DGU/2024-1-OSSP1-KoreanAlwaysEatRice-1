package com.example.menuw.Exception;

import com.example.menuw.dto.ResponseDto;
import org.springframework.http.HttpStatus;

public class Exception404 extends RuntimeException {
    public Exception404(String message) {
        super(message);
    }

    public ResponseDto<?> body() {
        return new ResponseDto<>(HttpStatus.NOT_FOUND, "notFound", null);
    }

    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}
