package com.example.menuw.service;

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
    @Value("${S3.baseurl}")
    private String baseURL;

    private final IngredientRepository ingredientRepository;

    public List<IngredientDto> findAllIngredient() {
        List<IngredientDto> ingredientList = ingredientRepository.findAll()
                .stream()
                .map(IngredientDto::toDto)
                .collect(Collectors.toList());

        return ingredientList
                    .stream()
                    .map(dto -> {
                        dto.setIngredientImageURL(baseURL);
                        return dto;
                    })
                    .collect(Collectors.toList());
    }

    public String findIngredientNameById(Long id) {
        return ingredientRepository.findById(id.intValue()).get().getIngredientName();
    }
}
