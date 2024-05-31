package com.kaer.menuw.data.repository

import com.kaer.menuw.data.datasource.MenuDataSource
import com.kaer.menuw.domain.entity.LikeMenu
import com.kaer.menuw.domain.entity.RecipeList
import com.kaer.menuw.domain.repository.MenuRepository
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(private val menuDataSource: MenuDataSource) :
    MenuRepository {

    override suspend fun postMenuRecipe(menuName: String): Result<RecipeList> = runCatching {
        menuDataSource.postMenuRecipe(menuName).toRecipeList()
    }

    override suspend fun getLikeMenuList(): Result<List<LikeMenu>> = runCatching {
        menuDataSource.getLikeMenuList().toMenuItem()
    }
}