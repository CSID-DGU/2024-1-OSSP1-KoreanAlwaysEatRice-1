package com.example.menuw.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MyPageUserInfoDto {
    String userName;
    String userNickname;
    String userImageUrl;
}
