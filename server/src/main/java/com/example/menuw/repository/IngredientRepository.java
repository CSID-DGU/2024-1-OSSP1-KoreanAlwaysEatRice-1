package com.example.menuw.repository;

import com.example.menuw.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findAllByIngredientType(String ingredientType);

    @Query("SELECT i FROM Ingredient i ORDER BY i.ingredientType")
    List<Ingredient> findAllOrderByType();
}
