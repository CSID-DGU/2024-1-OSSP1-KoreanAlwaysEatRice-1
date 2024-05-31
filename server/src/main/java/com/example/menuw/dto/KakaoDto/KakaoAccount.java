package com.example.menuw.dto.KakaoDto;

import lombok.Getter;

@Getter
public class KakaoAccount {
    private Boolean profile_nickname_needs_agreement;
    private Boolean profile_image_needs_agreement;
    private KakaoProfile profile;
}