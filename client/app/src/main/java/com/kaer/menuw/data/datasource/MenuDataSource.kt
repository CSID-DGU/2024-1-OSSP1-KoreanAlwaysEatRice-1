package com.kaer.menuw.data.datasource

import com.kaer.menuw.data.model.remote.response.ResponsePostRecipeDto

interface MenuDataSource {

    suspend fun postMenuRecipe(menuName: String): ResponsePostRecipeDto
}