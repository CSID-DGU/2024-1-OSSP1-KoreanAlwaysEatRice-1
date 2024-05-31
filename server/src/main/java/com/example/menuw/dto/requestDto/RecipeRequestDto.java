package com.example.menuw.dto.requestDto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeRequestDto {
    private String recipe;
    private String menuType;
    private List<Integer> ingredientList;
    private String menuName;
}