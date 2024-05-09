package com.example.menuw.service;

import com.example.menuw.domain.User;
import com.example.menuw.dto.KakaoUserInfoResponse;
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

    @Transactional(readOnly = true)
    public Integer isSignedUp(String token) {
        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(token);
        Optional<User> user = userRepository.findByKeyCode(userInfo.getId().toString());

        if (user.isEmpty()) {
            return user.get().getId();
        } else {
            return null;
        }
    }
}
