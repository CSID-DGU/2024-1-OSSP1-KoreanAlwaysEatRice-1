package com.example.menuw.dto;

import com.example.menuw.domain.Ingredient;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class IngredientDto {
    private Integer ingredientId;
    private String ingredientName;
    private String ingredientImageURL;
    private String ingredientType;

    public static IngredientDto toDto(Ingredient ingredient) {
        return IngredientDto.builder()
                .ingredientId(ingredient.getIngredientId())
                .ingredientName(ingredient.getIngredientName())
                .ingredientImageURL(ingredient.getIngredientImageURL())
                .ingredientType(ingredient.getIngredientType())
                .build();
    }

    public void setIngredientImageURL(String baseUrl, String ingredientImageURL){
        this.ingredientImageURL = baseUrl + ingredientImageURL;
    }
}

