package com.example.menuw.repository;

import com.example.menuw.domain.Ingredient;
import com.example.menuw.dto.IngredientDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findAllByIngredientType(String ingredientType);
}
