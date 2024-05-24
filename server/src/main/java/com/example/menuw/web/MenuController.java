package com.example.menuw.web;

import com.amazonaws.Response;
import com.example.menuw.dto.MenuDto;
import com.example.menuw.dto.MenuRequestDto;
import com.example.menuw.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    /*@PostMapping("/like")
    public ResponseEntity<Boolean> menuLike (
            @RequestBody MenuRequestDto menuRequestDto
            ) {
        return new ResponseEntity<>(menuService.menuLike(menuRequestDto), HttpStatus.OK);
    } */

}
