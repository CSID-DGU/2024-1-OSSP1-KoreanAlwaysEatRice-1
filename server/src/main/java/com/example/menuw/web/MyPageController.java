package com.example.menuw.web;

import com.example.menuw.dto.ResponseDto.MyPageUserInfoDto;
import com.example.menuw.dto.ResponseDto.ResponseDto;
import com.example.menuw.dto.ResponseDto.TokenDto;
import com.example.menuw.service.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
        private final KakaoAuthService kakaoAuthService;
    @GetMapping("/info")
    public ResponseDto<MyPageUserInfoDto> getUserInfo(@RequestHeader("Authorization") String accessToken){
        MyPageUserInfoDto myPageUserInfoDto = kakaoAuthService.getUserInfo(accessToken);

        return ResponseDto.res(HttpStatus.OK, "내 정보 가져오기를 성공하였습니다.", myPageUserInfoDto);
    }

    @PostMapping("/logout")
    public ResponseDto<TokenDto> logout(@RequestHeader("Authorization") String accessToken) {
        TokenDto tokenDto = kakaoAuthService.logout(accessToken);
        return ResponseDto.res(HttpStatus.OK, "로그아웃 성공", tokenDto);
    }

    @DeleteMapping("/withdrawal")
    public ResponseDto<?> withdrawal(@RequestHeader("Authorization") String accessToken) {
        Long id = kakaoAuthService.unlink(accessToken);
        return ResponseDto.res(HttpStatus.OK, "회원 탈퇴에 성공하였습니다.", Collections.emptyMap());
    }
}
