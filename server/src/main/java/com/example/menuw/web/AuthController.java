package com.example.menuw.web;

import com.example.menuw.service.JwtTokenProvider;
import com.example.menuw.service.KakaoAuthService;
import com.example.menuw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;
    private final KakaoAuthService kakaoAuthService;
    private final JwtTokenProvider jwtTokenProvider;

    //카카오 로그인을 위해 회원가입 여부 확인, 이미 회원이면 Jwt 토큰 발급
    @PostMapping("/login")
    public ResponseEntity<HashMap<Integer, String>> authCheck(@RequestHeader String accessToken) {
        Integer userId = kakaoAuthService.isSignedUp(accessToken); //유저 고유 번호 추출
        HashMap<Integer, String> map = new HashMap<>();
        map.put(userId, jwtTokenProvider.createToken(userId.toString()));
        return ResponseEntity.ok(map);
    }
}
