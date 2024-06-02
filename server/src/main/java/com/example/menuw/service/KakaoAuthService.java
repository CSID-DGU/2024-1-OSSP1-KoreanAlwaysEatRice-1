package com.example.menuw.service;

import com.example.menuw.domain.User;
import com.example.menuw.dto.KakaoDto.KakaoUnlinkResponse;
import com.example.menuw.dto.KakaoDto.KakaoUserInfoResponse;
import com.example.menuw.dto.ResponseDto.MyPageUserInfoDto;
import com.example.menuw.dto.ResponseDto.TokenDto;
import com.example.menuw.dto.UserDto;
import com.example.menuw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {
    private final KakaoUserInfo kakaoUserInfo;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;
    private final String USER_UNLINK_URL = "https://kapi.kakao.com/v1/user/unlink";
    private final WebClient webClient;
  
    @Value("${oauth.kakao.admin-key}")
    private String adminKey;

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

            userRepository.save(User.toUser(userdto));
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
        int id = Integer.parseInt(jwtTokenProvider.getUserPK(accessToken));
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        return MyPageUserInfoDto.builder()
                .userImageUrl(user.getProfile_image())
                .userName(user.getUsername())
                .userNickname(user.getNickname())
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

    public Long unlink(String accessToken) {
        int userId = Integer.parseInt(jwtTokenProvider.getUserPK(accessToken));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Flux<Long> id = webClient.post()
                .uri(USER_UNLINK_URL)
                .header("Authorization", "KakaoAK " + adminKey)
                .bodyValue("target_id_type=user_id&target_id=" + userId)
                .retrieve()
                .bodyToFlux(KakaoUnlinkResponse.class)
                .map(KakaoUnlinkResponse::getId);

        return id.blockFirst();
    }
}
