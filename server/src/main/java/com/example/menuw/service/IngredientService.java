package com.example.menuw.service;

import com.example.menuw.domain.Ingredient;
import com.example.menuw.dto.IngredientDto;
import com.example.menuw.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IngredientService {
    @Value("${S3.baseurl")
    private String baseurl;

    private final IngredientRepository ingredientRepository;

    public List<IngredientDto> findAllOrderByType() {
        List<Ingredient> ingredientList = ingredientRepository.findAllOrderByType();

        return ingredientList.stream().map(ingredient -> {
                    IngredientDto dto = IngredientDto.toDto(ingredient);
                    dto.setIngredientImageURL(baseurl, dto.getIngredientImageURL());
                    return dto;
                }).collect(Collectors.toList());
    }

    public List<IngredientDto> findByIngredientType(String ingredientType) {
        List<Ingredient> ingredientList = ingredientRepository.findAllByIngredientType(ingredientType);

        return ingredientList.stream()
                .map(IngredientDto::toDto)
                .collect(Collectors.toList());
    }
}
