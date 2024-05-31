package com.example.menuw.dto.KakaoDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoProfile {
    private String userName;
    private String userNickname;
    private String profile_image_url;
}
