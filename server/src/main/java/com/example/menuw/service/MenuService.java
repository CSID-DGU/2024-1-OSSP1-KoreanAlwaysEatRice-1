package com.example.menuw.service;

import com.example.menuw.dto.MenuDto;
import com.example.menuw.dto.MenuSimpleDto;
import com.example.menuw.dto.requestDto.MenuRequestDto;
import com.example.menuw.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RecipeApiService recipeApiService;

    public void decMenu(MenuRequestDto menuRequestDto) {
        MenuDto menuDto = menuRepository.findByMenuName(menuRequestDto.getMenuName());

        if (menuDto == null) { //DB에 없으면 새로 생성
            menuDto = recipeApiService.useMenuAPIByMenuName(menuRequestDto.getMenuName());
        }
        menuDto.setMenuLike(menuRequestDto.getMenuLike());

        menuRepository.save(MenuDto.toEntity(menuDto));
    }

    public List<MenuSimpleDto> getLikedMenuList() {
        return menuRepository.findAllLikedMenuList();
    }

}
