package com.kaer.menuw.data.service

import com.kaer.menuw.data.model.remote.response.ResponseGetIngredientDto
import retrofit2.http.GET

interface IngredientService {

    @GET("/Image")
    suspend fun getIngredient(): ResponseGetIngredientDto
}