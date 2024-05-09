package com.example.menuw.service;

import com.example.menuw.domain.User;
import com.example.menuw.dto.KakaoUserInfoResponse;
import com.example.menuw.dto.UserDto;
import com.example.menuw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {
    private final KakaoUserInfo kakaoUserInfo;
    private final UserRepository userRepository;

    @Transactional(readOnly = true) //카카오 로그인을 위해 회원가입 여부 확인, 회원이면 JWT 토큰 발급
    public Integer isSignedUp(String token){
        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(token);
        Optional<User> user = userRepository.findByKeyCode(userInfo.getId().toString());

        if (user.isPresent()) {
            return user.get().getId();
        } else {
            this.createUser(token);
            return null;
        }
    }

    @Transactional
    public UserDto createUser(String token){
        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(token);

        UserDto userdto = UserDto.builder()
                .id(userInfo.getId().intValue())
                .nickname(userInfo.getProperties().getNickname())
                .profile_image(userInfo.getProperties().getProfile_image())
                .thumbnail_image(userInfo.getProperties().getThumbnail_image())
                .build();

        userRepository.save(UserDto.toDomain(userdto));

        return userdto;
    }
}
