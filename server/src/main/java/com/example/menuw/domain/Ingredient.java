package com.example.menuw.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Ingredient {
    @Id @GeneratedValue
    @Column(name = "ingredientId")
    private Integer ingredientId;
    private String ingredientType;
    private String ingredientName;
    private String ingredientImageURL;
}
