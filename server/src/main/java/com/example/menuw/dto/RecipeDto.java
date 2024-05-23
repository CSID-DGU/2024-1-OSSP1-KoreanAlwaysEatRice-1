package com.example.menuw.dto;

import lombok.Builder;
import java.util.List;

@Builder
public class RecipeDto {
    private List<String> recipeList;
    private List<String> recipeImageList;
}
