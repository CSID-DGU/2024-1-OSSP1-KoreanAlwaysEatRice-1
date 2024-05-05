package com.example.menuw.web;

import com.example.menuw.service.KakaoAuthService;
import com.example.menuw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/home/login")
public class AuthController {
    private final UserService userService;
    private final KakaoAuthService kakaoAuthService;

}
