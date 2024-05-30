package com.example.menuw.web;

import com.example.menuw.dto.RecipeDto;
import com.example.menuw.dto.ResponseDto.ResponseDto;
import com.example.menuw.dto.requestDto.RecipeRequestDto;
import com.example.menuw.service.RecipeApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecipeApiController {
    private final RecipeApiService recipeApiService;

    @PostMapping("/menu/recipe")
    public ResponseDto<RecipeDto> getRecipeByMenuName(@RequestBody RecipeRequestDto recipeRequestDto) {
        return ResponseDto.res(HttpStatus.OK, "레시피 가져오기를 성공하였습니다.", recipeApiService.getRecipes(recipeRequestDto.getMenuName()));
    }
}
