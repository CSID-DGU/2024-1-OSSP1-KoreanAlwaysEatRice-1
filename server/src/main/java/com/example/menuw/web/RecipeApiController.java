package com.example.menuw.web;

import com.example.menuw.dto.MenuDto;
import com.example.menuw.dto.RecipeDto;
import com.example.menuw.dto.ResponseDto;
import com.example.menuw.dto.requestDto.MenuRequestDto;
import com.example.menuw.service.RecipeApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecipeApiController {
    private final RecipeApiService recipeApiService;

    @PostMapping("/api/recipe")
    public ResponseEntity<List<MenuDto>> getMenuByIngredients(@RequestBody List<Long> ingredientId) {
        return ResponseEntity.ok(recipeApiService.getMenu(ingredientId));
    }

    @PostMapping("/menu/recipe")
    public ResponseDto<RecipeDto> getRecipeByMenuName(@RequestBody MenuRequestDto menuRequestDto) {
        return ResponseDto.res(HttpStatus.OK, "레시피 가져오기를 성공하였습니다.", recipeApiService.getRecipes(menuRequestDto.getMenuName()));
    }
}
