package com.example.menuw.web;

import com.example.menuw.dto.MenuDto;
import com.example.menuw.dto.RecipeDto;
import com.example.menuw.dto.ResponseDto.ResponseDto;
import com.example.menuw.dto.requestDto.RecipeRequestDto;
import com.example.menuw.dto.requestDto.MenuRequestDto;
import com.example.menuw.service.RecipeApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class RecipeApiController {
    private final RecipeApiService recipeApiService;
    /*
        @PostMapping("/api/recipe")
        public ResponseEntity<List<MenuDto>> getMenuByIngredients(@RequestBody List<Integer> ingredientId,@RequestBody String recipe,@RequestBody String menuType ) {
            System.out.println("getMenuByIngredients" + ingredientId + " " + recipe + " " + menuType);
            return ResponseEntity.ok().body(recipeApiService.getMenu(ingredientId,recipe, menuType));
        }*/
    @PostMapping("/menu/list")
    public ResponseEntity<List<MenuDto>> createRecipe(@RequestBody RecipeRequestDto recipeRequest) {
        // 처리 결과에 따라 응답 반환
        return ResponseEntity.ok().body(recipeApiService.getMenu(recipeRequest.getIngredientList(),
                recipeRequest.getMenuType(), recipeRequest.getRecipe()));
    }

    @PostMapping("/menu/recipe")
    public ResponseDto<RecipeDto> getRecipeByMenuName(@RequestBody RecipeRequestDto recipeRequestDto) {
        return ResponseDto.res(HttpStatus.OK, "레시피 가져오기를 성공하였습니다.", recipeApiService.getRecipes(recipeRequestDto.getMenuName()));
    }
}
