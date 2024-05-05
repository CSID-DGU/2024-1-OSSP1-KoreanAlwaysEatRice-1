package com.example.menuw.service;

import com.example.menuw.Exception.ResponseCode;
import com.example.menuw.Exception.UserException;
import com.example.menuw.domain.User;
import com.example.menuw.dto.KakaoUserInfoResponse;
import com.example.menuw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {
    private final KakaoUserInfo kakaoUserInfo;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Integer isSignedUp(String token) throws UserException {
        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(token);
        User user = userRepository.findByKeyCode(userInfo.getId().toString())
                .orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));
        return user.getId();
    }
}
