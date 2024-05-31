package com.example.menuw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuSimpleDto {
    private int menuId;
    private String menuName;
    private String menuImageURL;
    private String ingredients;
}
