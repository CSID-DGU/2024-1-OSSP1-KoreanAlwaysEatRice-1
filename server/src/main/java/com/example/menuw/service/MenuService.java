package com.example.menuw.service;

import com.example.menuw.dto.MenuDto;
import com.example.menuw.dto.MenuRequestDto;
import com.example.menuw.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Boolean menuLike(MenuRequestDto menuRequestDto) {

        MenuDto menu = menuRepository.findByMenuName(menuRequestDto.getMenuName());

        //menu.updateMenuLike(menuRequestDto.getMenuLike());

        return true;
    }
}
