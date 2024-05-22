package com.example.menuw.web;

import com.example.menuw.dto.RecipeDto;
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
    public ResponseEntity<Mono<List<RecipeDto>>> getRecipeByIngredients(@RequestBody List<Long> ingredientId) {
        return ResponseEntity.ok(recipeApiService.fetchRecipes(recipeApiService.getIngredients(ingredientId)));
    }
}
