package com.example.menuw.web;

import com.example.menuw.dto.MenuDto;
import com.example.menuw.service.RecipeApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecipeApiController {
    private final RecipeApiService recipeApiService;

    @PostMapping("/api/recipe")
    public ResponseEntity<List<MenuDto>> getRecipeByIngredients(@RequestBody List<Long> ingredientId) {
        return ResponseEntity.ok(recipeApiService.getMenu(ingredientId));
    }
}
