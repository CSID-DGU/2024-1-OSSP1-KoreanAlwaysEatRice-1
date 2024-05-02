package com.example.menuw.dto;

import com.example.menuw.domain.Ingredient;

public class IngredientDto {
    public final String ingredientImageURL;

    public IngredientDto(Ingredient ingredient) {
        this.ingredientImageURL = ingredient.getIngredientImageURL();
    }
}
