package com.example.menuw.service;

import com.example.menuw.dto.MenuDto;
import com.example.menuw.dto.requestDto.MenuRequestDto;
import com.example.menuw.dto.ResponseDto.ResponseDto;
import com.example.menuw.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public int decMenu(MenuRequestDto menuRequestDto) {
        MenuDto menu = menuRepository.findByMenuName(menuRequestDto.getMenuName());

        if (menu != null) {
            menu.updateMenuLike(menuRequestDto.getMenuLike());
            return 2;
        }
        else {
            return 1;
        }
    }


    public List<MenuDto> getLikedMenuList() {
        List<MenuDto> likedMenuList = menuRepository.findAllLikedMenuList();
        return likedMenuList;
    }


}
