package com.example.menuw.repository;

import com.example.menuw.domain.Refrigerator;
import com.example.menuw.dto.RefrigeratorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Integer> {
    @Query("SELECT r FROM Refrigerator r WHERE r.user.id = :id")
    RefrigeratorDto findRefrigeratorByUserId(Integer id);
}
