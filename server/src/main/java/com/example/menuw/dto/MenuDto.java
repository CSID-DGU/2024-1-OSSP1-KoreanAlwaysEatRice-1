package com.example.menuw.dto;

import com.example.menuw.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.menuw.domain.Menu;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private int menuId;
    private String menuName;
    private String menuImageURL;
    private String ingredients;
    private String recipe;  //조리방식
    private String recipeImage;
    private String menuType;    //요리종류
    private String serving;
    private int cal;
    private int na;
    private double similarity;  //유사도
    private boolean recommend; // 추천
    private User user;

    private Integer menuLike;

    public MenuDto(int menuLike, String menuName, int menuId, String ingredients, String recipeImage) {
        this.menuLike = menuLike;
        this.menuName = menuName;
        this.menuId = menuId;
        this.ingredients = ingredients;
        this.recipeImage = recipeImage;
    }
    public MenuDto(int menuId, String menuName, String menuImageURL, String ingredients) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuImageURL = menuImageURL;
        this.ingredients = ingredients;
    }

    public void updateMenuLike(Integer menuLike) {
        this.menuLike = menuLike;
    }

    public static Menu toEntity(MenuDto menuDto) {
        return Menu.builder()
                .menuId(menuDto.getMenuId())
                .menuLike(menuDto.getMenuLike())
                .menuName(menuDto.getMenuName())
                .menuImageURL(menuDto.getMenuImageURL())
                .ingredients(menuDto.getIngredients())
                .recipe(menuDto.getRecipe())
                .menuType(menuDto.getMenuType())
                .cal(menuDto.getCal())
                .na(menuDto.getNa())
                .build();
    }
}
