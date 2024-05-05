package com.example.menuw.service;

import com.example.menuw.domain.Ingredient;
import com.example.menuw.dto.IngredientDto;
import com.example.menuw.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public List<IngredientDto> findByIngredientType(String ingredientType) {
        List<List<IngredientDto>> totalList = new ArrayList<>();
        List<Ingredient> ingredientList = ingredientRepository.findAllByIngredientType(ingredientType);

        return ingredientList.stream()
                .map(IngredientDto::toDto)
                .collect(Collectors.toList());
    }
}
