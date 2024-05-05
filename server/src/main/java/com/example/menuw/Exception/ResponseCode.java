package com.example.menuw.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseCode {
    USER_NOT_FOUND("USER_NOT_FOUND", "사용자를 찾을 수 없습니다.");

    private final String code;
    private final String message;
}
