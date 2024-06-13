package com.kaer.menuw.domain.repository

import com.kaer.menuw.domain.entity.LikeMenu
import com.kaer.menuw.domain.entity.RecipeList
import com.kaer.menuw.domain.entity.RecommendMenu

interface MenuRepository {

    suspend fun postMenuRecipe(menuName: String): Result<RecipeList>

    suspend fun getLikeMenuList(): Result<List<LikeMenu>>

    suspend fun postRecommendMenuList(
        recipe: String,
        menuType: String,
        ingredientList: ArrayList<Int>
    ): Result<List<RecommendMenu>>
}