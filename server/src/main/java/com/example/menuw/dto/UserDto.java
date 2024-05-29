package com.example.menuw.dto;

import com.example.menuw.domain.User;
import lombok.Builder;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
public class UserDto {
    public int id;

    public String email;
    public String nickname;
    public String profile_image;
    public String thumbnail_image;

    public static User toDomain(UserDto userDto) {
        return User.builder()
                .id(userDto.id)
                .nickname(userDto.nickname)
                .profile_image(userDto.profile_image)
                .thumbnail_image(userDto.thumbnail_image)
                .build();
    }
}
