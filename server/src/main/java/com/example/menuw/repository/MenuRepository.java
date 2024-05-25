package com.example.menuw.repository;

import com.example.menuw.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    //MenuDto findByMenuName(String menuName);
}
