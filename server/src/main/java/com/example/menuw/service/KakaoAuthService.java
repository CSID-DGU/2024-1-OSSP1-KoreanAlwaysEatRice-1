package com.example.menuw.service;

import com.example.menuw.domain.User;
import com.example.menuw.dto.KakaoDto.KakaoUserInfoResponse;
import com.example.menuw.dto.ResponseDto.MyPageUserInfoDto;
import com.example.menuw.dto.ResponseDto.TokenDto;
import com.example.menuw.dto.UserDto;
import com.example.menuw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {
    private final KakaoUserInfo kakaoUserInfo;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true) //카카오 로그인을 위해 회원가입 여부 확인, 회원이면 JWT 토큰 발급
    public TokenDto isSignedUp(String accessToken){
        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(accessToken);
        Optional<User> user = userRepository.findById(userInfo.getId().intValue());

        if (user.isEmpty()) { //회원가입
            UserDto userdto = UserDto.builder()
                    .id(userInfo.getId().intValue())
                    .email(userInfo.getKakao_account().toString())
                    .nickname(userInfo.getProperties().getNickname())
                    .profile_image(userInfo.getProperties().getProfile_image())
                    .thumbnail_image(userInfo.getProperties().getThumbnail_image())
                    .build();

            userRepository.save(UserDto.toDomain(userdto));
        }

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(jwtTokenProvider.createToken(userInfo.getId().toString()))
                .build();
    }

    @Transactional(readOnly = true)
    public MyPageUserInfoDto getUserInfo(String accessToken) {
        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(accessToken);

        return MyPageUserInfoDto.builder()
                .userImageUrl(userInfo.getProperties().getProfile_image())
                .userName(userInfo.getKakao_account().getEmail())
                .userNickname(userInfo.getProperties().getNickname())
                .build();
    }
}
