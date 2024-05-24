package com.example.menuw.repository;

import com.example.menuw.domain.Menu;
import com.example.menuw.dto.MenuDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    MenuDto findByMenuName(String menuName);
}
