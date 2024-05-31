package com.kaer.menuw.domain.repository

import com.kaer.menuw.domain.entity.LikeMenu
import com.kaer.menuw.domain.entity.RecipeList

interface MenuRepository {

    suspend fun postMenuRecipe(menuName: String): Result<RecipeList>

    suspend fun getLikeMenuList(): Result<List<LikeMenu>>
}