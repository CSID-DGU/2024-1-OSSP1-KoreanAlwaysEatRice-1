package com.example.menuw.dto;

import com.example.menuw.domain.User;
import lombok.Builder;

@Builder
public class UserDto {
    public String keyCode;
    public int id;

    public String nickname;
    public String profile_image;
    public String thumbnail_image;

    public static User toDomain(UserDto userDto) {
        return User.builder()
                .keyCode(userDto.keyCode)
                .id(userDto.id)
                .nickname(userDto.nickname)
                .profile_image(userDto.profile_image)
                .thumbnail_image(userDto.thumbnail_image)
                .build();
    }
}
