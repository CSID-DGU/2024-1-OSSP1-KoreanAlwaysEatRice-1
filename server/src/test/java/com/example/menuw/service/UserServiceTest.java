package com.example.menuw.service;

import com.example.menuw.domain.User;
import com.example.menuw.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        User user = new User();
        user.setUserName("kim");

        //when
        Long userId = userService.join(user);

        //then
        assertEquals(user, userRepository.findOne(userId));
    }
}