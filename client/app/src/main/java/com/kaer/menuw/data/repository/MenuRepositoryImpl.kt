package com.kaer.menuw.data.repository

import com.kaer.menuw.data.datasource.MenuDataSource
import com.kaer.menuw.domain.entity.LikeMenu
import com.kaer.menuw.domain.entity.RecipeList
import com.kaer.menuw.domain.entity.RecommendMenu
import com.kaer.menuw.domain.repository.MenuRepository
import timber.log.Timber
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(private val menuDataSource: MenuDataSource) :
    MenuRepository {

    override suspend fun postMenuRecipe(menuName: String): Result<RecipeList> = runCatching {
        menuDataSource.postMenuRecipe(menuName).toRecipeList()
    }

    override suspend fun getLikeMenuList(): Result<List<LikeMenu>> = runCatching {
        menuDataSource.getLikeMenuList().toMenuItem()
    }

    override suspend fun postRecommendMenuList(
        recipe: String,
        menuType: String,
        ingredientList: ArrayList<Int>
    ): Result<List<RecommendMenu>> = runCatching {
        menuDataSource.postRecommendMenuList(recipe, menuType, ingredientList)
            .map { it.toMenuList() }
    }

    override suspend fun patchMenuLike(
        contentType: String,
        authorization: String,
        menuName: String,
        menuLike: Int
    ): Result<Boolean> = runCatching {
        menuDataSource.patchMenuLike(contentType, authorization, menuName, menuLike)
    }.fold(
        onSuccess = {
            Timber.d("레시피 저장에 성공")
            Result.success(true)
        },
        onFailure = {
            Timber.d("레시피 저장에 실패 -> ${it.message}")
            Result.failure(it)
        }
    )
}