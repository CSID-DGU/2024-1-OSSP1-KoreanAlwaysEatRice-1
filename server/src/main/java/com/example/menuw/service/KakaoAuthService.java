package com.example.menuw.service;

import com.example.menuw.domain.User;
import com.example.menuw.dto.KakaoDto.KakaoUserInfoResponse;
import com.example.menuw.dto.ResponseDto.MyPageUserInfoDto;
import com.example.menuw.dto.ResponseDto.TokenDto;
import com.example.menuw.dto.UserDto;
import com.example.menuw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {
    private final KakaoUserInfo kakaoUserInfo;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

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

        TokenDto tokenDto = TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(jwtTokenProvider.createToken(userInfo.getId().toString()))
                .build();

        redisTemplate.opsForValue().set("RT:"+userInfo.getId().intValue(), tokenDto.getRefreshToken(), jwtTokenProvider.getExpiration(accessToken), TimeUnit.MILLISECONDS);

        return tokenDto;
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

    @Transactional
    public TokenDto logout(String accessToken) {
        if (!jwtTokenProvider.validateToken(accessToken)) {
            throw new IllegalArgumentException("로그아웃 : 유효하지 않은 토큰입니다.");
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        //Redis에서 User id로 저장된 refresh Token이 있는지 여부를 확인 후 삭제
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            redisTemplate.delete("RT:" + authentication.getName());
        }

        Long expiration = jwtTokenProvider.getExpiration(accessToken);
        redisTemplate.opsForValue().set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(authentication.getName())
                .build();
    }
}
