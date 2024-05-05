package com.example.menuw.Exception;

public class UserException extends Exception {
    private ResponseCode responseCode;

    public UserException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}
