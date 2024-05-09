package com.example.menuw.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class RecipeDto {
    String recipeName;
    String recipeIngredients;
    String imageUrl;
}
