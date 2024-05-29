package com.example.menuw.repository;

import com.example.menuw.domain.User;
import com.example.menuw.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
}
