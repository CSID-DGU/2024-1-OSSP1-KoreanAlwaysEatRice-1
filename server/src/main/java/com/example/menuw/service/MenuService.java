package com.example.menuw.service;

import com.example.menuw.domain.User;
import com.example.menuw.dto.MenuDto;
import com.example.menuw.dto.MenuSimpleDto;
import com.example.menuw.dto.requestDto.MenuRequestDto;
import com.example.menuw.repository.MenuRepository;
import com.example.menuw.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RecipeApiService recipeApiService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void decMenu(MenuRequestDto menuRequestDto, String refreshToken) {
        Optional<User> user = userRepository.findById(Integer.parseInt(jwtTokenProvider.getUserPK(refreshToken)));
        MenuDto menuDto = menuRepository.findByMenuName(menuRequestDto.getMenuName());

        if (menuDto == null) { //DB에 없으면 새로 생성
            menuDto = recipeApiService.useMenuAPIByMenuName(menuRequestDto.getMenuName());
        }
        menuDto.setMenuLike(menuRequestDto.getMenuLike());
        menuDto.setUser(user.get());

        menuRepository.save(MenuDto.toEntity(menuDto));
    }

    public List<MenuSimpleDto> getLikedMenuList() {
        return menuRepository.findAllLikedMenuList();
    }
}
