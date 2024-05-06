package com.example.menuw.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}") //application.properties 등에 보관한다.
    private String secretKey;


}
