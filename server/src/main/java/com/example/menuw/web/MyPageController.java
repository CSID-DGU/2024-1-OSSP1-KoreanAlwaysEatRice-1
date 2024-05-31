package com.example.menuw.web;

import com.example.menuw.dto.KakaoDto.KakaoProfile;
import com.example.menuw.dto.ResponseDto.ResponseDto;
import com.example.menuw.service.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
        private final KakaoAuthService kakaoAuthService;
    @GetMapping("/info")
    public ResponseDto<KakaoProfile> getUserInfo(@RequestHeader("Authorization") String accessToken){
        return ResponseDto.res(HttpStatus.OK, "내 정보 가져오기를 성공하였습니다.", kakaoAuthService.getUserInfo(accessToken));
    }

}
