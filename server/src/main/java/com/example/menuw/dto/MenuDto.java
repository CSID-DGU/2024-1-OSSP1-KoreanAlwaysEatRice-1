package com.example.menuw.dto;

import com.example.menuw.domain.Ingredient;
import com.example.menuw.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private int menuId;
    private String menuName;
    private String menuImage;
    private String ingredients;
    private String recipe;
    private String recipeImage;
    private String menuType;
    private String serving;
    private int cal;
    private int na;

    private Integer menuLike;

    public MenuDto(Integer menuLike, String menuName, int menuId, String ingredients, String recipeImage) {
        this.menuLike = menuLike;
        this.menuName = menuName;
        this.menuId = menuId;
        this.ingredients = ingredients;
        this.recipeImage = recipeImage;
    }

    public MenuDto(int menuId, String menuName, String menuImage, String ingredients) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuImage = menuImage;
        this.ingredients = ingredients;
    }

    public void updateMenuLike(Integer menuLike) {
        this.menuLike = menuLike;
    }
}
