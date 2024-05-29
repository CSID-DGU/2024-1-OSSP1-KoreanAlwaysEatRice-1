package com.example.menuw.web;

import com.amazonaws.Response;
import com.example.menuw.dto.ResponseDto.ResponseDto;
import com.example.menuw.dto.ResponseDto.TokenDto;
import com.example.menuw.service.JwtTokenProvider;
import com.example.menuw.service.KakaoAuthService;
import com.example.menuw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final KakaoAuthService kakaoAuthService;

    //카카오 로그인을 위해 회원가입 여부 확인, 이미 회원이면 Jwt 토큰 발급
    @PostMapping("/login")
    public ResponseDto<TokenDto> authCheck(@RequestHeader("Authorization") String accessToken) {
        TokenDto tokenDto = kakaoAuthService.isSignedUp(accessToken); //유저 고유 번호 추출

        return ResponseDto.res(HttpStatus.OK, "소셜 로그인 성공", tokenDto);
    }
}
