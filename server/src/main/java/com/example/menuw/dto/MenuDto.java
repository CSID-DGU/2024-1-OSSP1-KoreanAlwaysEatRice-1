package com.example.menuw.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuDto {
    int menuId;
    String menuName;
    String menuImage;
    String ingredients;
    String recipe;
    String recipeImage;
    String menuType;
    String serving;
    int cal;
    int na;
}
