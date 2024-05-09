package com.example.menuw.service;

import com.example.menuw.domain.User;
import com.example.menuw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Integer createUser(String email) {
        User user = User.builder()
                .user_nickname(email)
                .build();

        userRepository.save(user);
        return user.getId();
    }
}
