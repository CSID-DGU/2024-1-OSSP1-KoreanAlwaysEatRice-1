package com.example.menuw.service;

import com.example.menuw.domain.Menu;
import com.example.menuw.dto.MenuRequestDto;
import com.example.menuw.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Boolean menuLike(MenuRequestDto menuRequestDto) { //컨트롤러에서 리퀘스트Dto받아서 매개변수로 쓸거임

        Menu menu = menuRepository.findByMenuName(menuRequestDto.getMenuName()); //프론트에서 받아온 메뉴 이름으로 인스턴스(줄.한줄임..) 찾을거임

        menu.updateMenuLike(menuRequestDto.getMenuLike());
        //내가 찾아온 인스턴스(menu)의 Like를 바꾸는것.


        return true;
    }
}
