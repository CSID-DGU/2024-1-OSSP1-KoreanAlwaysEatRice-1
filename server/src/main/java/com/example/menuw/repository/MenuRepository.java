package com.example.menuw.repository;

import com.example.menuw.domain.Menu;
import com.example.menuw.dto.MenuDto;
import com.example.menuw.dto.MenuSimpleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT new com.example.menuw.dto.MenuDto(menuLike, menuName, menuId, ingredients, menuImageURL) " + "FROM Menu WHERE menuName = :menuName")
    MenuDto findByMenuName(@Param("menuName") String menuName);

    @Query("SELECT new com.example.menuw.dto.MenuSimpleDto(menuId, menuName, menuImageURL, ingredients) " + "FROM Menu WHERE menuLike = 2")
    List<MenuSimpleDto> findAllLikedMenuList();

}
