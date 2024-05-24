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

    private final MenuService menuService; //나 이 서비스 쓸거임

    @PostMapping("/like") //프론트에서 이거를 보고 여기로 찾아온다
    public ResponseEntity<Boolean> menuLike (
            @RequestBody MenuRequestDto menuRequestDto //프론트에서 주는 데이터를 컨트롤러가 받아서 requestDto에 저장
            ) {
        return new ResponseEntity<>(menuService.menuLike(menuRequestDto), HttpStatus.OK); //이제 이 서비스단에 있는 함수를 쓸거임
    } //컨트롤러의 리턴은 프론트로 보내는것

}
