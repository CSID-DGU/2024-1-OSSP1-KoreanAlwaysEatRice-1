package com.example.menuw.service;

import com.example.menuw.Exception.ResponseCode;
import com.example.menuw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String keyCode) throws UsernameNotFoundException {
        return userRepository.findByKeyCode(keyCode)
                .orElseThrow(() -> new UsernameNotFoundException(ResponseCode.USER_NOT_FOUND.getMessage()));
    }
}
