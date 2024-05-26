package com.kaer.menuw.domain.usecase

import com.kaer.menuw.domain.repository.IngredientRepository
import javax.inject.Inject

class GetIngredientUseCase @Inject constructor(private val repository: IngredientRepository) {

    suspend operator fun invoke() = repository.getIngredient()
}