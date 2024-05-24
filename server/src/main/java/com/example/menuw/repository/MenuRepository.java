package com.example.menuw.repository;

import com.example.menuw.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< feature/#24-feat-Response

public interface MenuRepository extends JpaRepository<Menu, Integer> {

=======
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu findByMenuName(String menuName);

>>>>>>> develop
}
