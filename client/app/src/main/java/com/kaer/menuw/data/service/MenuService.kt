package com.kaer.menuw.data.service

import com.kaer.menuw.data.model.remote.response.ResponsePostRecipeDto
import retrofit2.http.Body
import retrofit2.http.POST

interface MenuService {

    @POST("/menu/recipe")
    suspend fun postMenuRecipe(@Body menuName: String): ResponsePostRecipeDto
}