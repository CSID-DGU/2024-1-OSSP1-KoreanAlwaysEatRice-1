package com.kaer.menuw.data.datasource

import com.kaer.menuw.data.model.remote.response.ResponseGetLikeMenuListDto
import com.kaer.menuw.data.model.remote.response.ResponsePatchMenuLikeDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecipeDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecommendMenuListDto

interface MenuDataSource {

    suspend fun postMenuRecipe(menuName: String): ResponsePostRecipeDto

    suspend fun getLikeMenuList(): ResponseGetLikeMenuListDto

    suspend fun postRecommendMenuList(
        recipe: String,
        menuType: String,
        ingredientList: ArrayList<Int>
    ): List<ResponsePostRecommendMenuListDto>

    suspend fun patchMenuLike(contentType: String, authorization: String, menuName: String, menuLike: Int): ResponsePatchMenuLikeDto
}