package com.kaer.menuw.data.repository

import com.kaer.menuw.data.datasource.IngredientDataSource
import com.kaer.menuw.domain.entity.Ingredient
import com.kaer.menuw.domain.repository.IngredientRepository
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(private val ingredientDataSource: IngredientDataSource) :
    IngredientRepository {

    override suspend fun getIngredient(): Result<List<Ingredient>> = runCatching {
        ingredientDataSource.getIngredient().toIngredient()
    }
}