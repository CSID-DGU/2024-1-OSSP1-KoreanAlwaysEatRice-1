package com.example.menuw.web;

import com.example.menuw.domain.Ingredient;
import com.example.menuw.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RefrigeratorController {
    private final IngredientService ingredientService;

}
