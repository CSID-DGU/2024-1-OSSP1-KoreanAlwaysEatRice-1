package com.example.menuw.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Ingredient {
    private Integer ingredientId;
    private String ingredientType;
    @Id @GeneratedValue
    private String ingredientName;
    private String ingredientImageURL;
}
