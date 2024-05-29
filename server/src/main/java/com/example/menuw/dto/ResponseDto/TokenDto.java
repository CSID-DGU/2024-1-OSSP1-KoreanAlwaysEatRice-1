package com.example.menuw.dto.ResponseDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {
    public String accessToken;
    public String refreshToken;
}
