package com.kaer.menuw.data.datasource.impl

import com.kaer.menuw.data.datasource.IngredientDataSource
import com.kaer.menuw.data.model.remote.response.ResponseGetIngredientDto
import com.kaer.menuw.data.service.IngredientService
import javax.inject.Inject

class IngredientDataSourceImpl @Inject constructor(private val apiService: IngredientService) :
    IngredientDataSource {

    override suspend fun getIngredient(): ResponseGetIngredientDto = apiService.getIngredient()
}