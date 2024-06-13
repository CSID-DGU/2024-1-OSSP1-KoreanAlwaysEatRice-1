package com.kaer.menuw.data.datasource.impl

import com.kaer.menuw.data.datasource.MenuDataSource
import com.kaer.menuw.data.model.remote.request.RequestPostRecipeDto
import com.kaer.menuw.data.model.remote.request.RequestPostRecommendMenuListDto
import com.kaer.menuw.data.model.remote.response.ResponseGetLikeMenuListDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecipeDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecommendMenuListDto
import com.kaer.menuw.data.service.MenuService
import javax.inject.Inject

class MenuDataSourceImpl @Inject constructor(private val apiService: MenuService) : MenuDataSource {

    override suspend fun postMenuRecipe(menuName: String): ResponsePostRecipeDto =
        apiService.postMenuRecipe(
            RequestPostRecipeDto(menuName)
        )

    override suspend fun getLikeMenuList(): ResponseGetLikeMenuListDto =
        apiService.getLikeMenuList()

    override suspend fun postRecommendMenuList(
        recipe: String,
        menuType: String,
        ingredientList: ArrayList<Int>
    ): List<ResponsePostRecommendMenuListDto> = apiService.postRecommendMenuList(
        RequestPostRecommendMenuListDto(recipe, menuType, ingredientList)
    )
}