package com.example.menuw.service;

import com.example.menuw.domain.Ingredient;
import com.example.menuw.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Transactional
    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public List<Ingredient> findIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient findOne(Long ingredientId) {
        return ingredientRepository.findOne(ingredientId);
    }
}
