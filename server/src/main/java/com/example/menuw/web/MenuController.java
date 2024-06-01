package com.example.menuw.web;

import com.example.menuw.dto.MenuSimpleDto;
import com.example.menuw.dto.requestDto.MenuRequestDto;
import com.example.menuw.dto.ResponseDto.ResponseDto;
import com.example.menuw.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PatchMapping("/like")
    public ResponseDto<Object> menuLike(@RequestHeader("Authorization") String accessToken, @RequestBody MenuRequestDto menuRequestDto) {
        menuService.decMenu(menuRequestDto, accessToken);
        return ResponseDto.res(HttpStatus.OK, "레시피 저장에 성공하였습니다.", Collections.emptyMap());
    }

    @GetMapping("/like/list")
    public ResponseDto<List<MenuSimpleDto>> getLikedMenus() {
        List<MenuSimpleDto> response = menuService.getLikedMenuList();
        return ResponseDto.res(HttpStatus.OK, "좋게 평가한 레시피 불러오기에 성공하였습니다.", response);
    }
}
