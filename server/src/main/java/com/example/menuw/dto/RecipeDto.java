package com.example.menuw.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter @Setter
public class RecipeDto {
    private List<String> recipeList;
    private List<String> recipeImageList;
}
