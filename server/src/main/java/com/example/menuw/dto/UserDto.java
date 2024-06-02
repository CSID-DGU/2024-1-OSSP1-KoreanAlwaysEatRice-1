package com.example.menuw.dto;

import com.example.menuw.domain.User;
import lombok.Builder;

@Builder
public class UserDto {
    public Long id;

    public String email;
    public String nickname;
    public String profile_image;
    public String thumbnail_image;

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .profile_image(user.getProfile_image())
                .thumbnail_image(user.getThumbnail_image())
                .build();
    }
}
