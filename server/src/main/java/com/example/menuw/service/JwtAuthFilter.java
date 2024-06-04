package com.example.menuw.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor //토큰 필터링을 자동으로 하는 서비스
public class JwtAuthFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //클라이언트의 API 요청 헤더에서 토큰 추출
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        //유효성 검사 후 SecurityContext에 저장
        if (token != null && jwtTokenProvider.validateToken(token)) {
            //Redis에서 해당 accessToken logout 여부를 확인
            String isLogout = (String) redisTemplate.opsForValue().get(token);

            if (ObjectUtils.isEmpty(isLogout)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        //다음 필터링
        chain.doFilter(request, response);
    }
}
