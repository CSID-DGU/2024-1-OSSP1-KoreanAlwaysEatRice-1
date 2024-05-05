package com.example.menuw.service;

import com.example.menuw.dto.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class KakaoUserInfo {
    private final WebClient webClient;
    private static final String USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    public KakaoUserInfoResponse getUserInfo(String token) {
        Flux<KakaoUserInfoResponse> response = webClient.get()
                .uri(USER_INFO_URI)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToFlux(KakaoUserInfoResponse.class);
        return response.blockFirst();
    }
}
