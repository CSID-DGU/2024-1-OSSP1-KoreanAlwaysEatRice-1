package com.kaer.menuw.data.datasource.impl

import com.kaer.menuw.data.datasource.MenuDataSource
import com.kaer.menuw.data.model.remote.request.RequestPostRecipeDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecipeDto
import com.kaer.menuw.data.service.MenuService
import javax.inject.Inject

class MenuDataSourceImpl @Inject constructor(private val apiService: MenuService) : MenuDataSource {

    override suspend fun postMenuRecipe(menuName: String): ResponsePostRecipeDto =
        apiService.postMenuRecipe(
            RequestPostRecipeDto(menuName)
        )
}