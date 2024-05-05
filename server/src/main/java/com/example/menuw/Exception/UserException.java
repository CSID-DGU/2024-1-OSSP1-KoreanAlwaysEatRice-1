package com.example.menuw.Exception;

import lombok.RequiredArgsConstructor;

public class UserException extends Exception {
    private final ResponseCode responseCode;

    public UserException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}
