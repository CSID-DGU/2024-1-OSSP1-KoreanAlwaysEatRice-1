package com.kaer.menuw.data.datasource

import com.kaer.menuw.data.model.remote.response.ResponseGetIngredientDto

interface IngredientDataSource {

    suspend fun getIngredient(): ResponseGetIngredientDto
}