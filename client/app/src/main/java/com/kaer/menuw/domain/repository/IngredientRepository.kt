package com.kaer.menuw.domain.repository

import com.kaer.menuw.domain.entity.Ingredient

interface IngredientRepository {

    suspend fun getIngredient(): Result<List<Ingredient>>
}