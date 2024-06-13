package com.kaer.menuw.domain.usecase

import com.kaer.menuw.domain.repository.MenuRepository
import javax.inject.Inject

class PostRecommendMenuListUseCase @Inject constructor(private val repository: MenuRepository) {

    suspend operator fun invoke(
        recipe: String,
        menuType: String,
        ingredientList: ArrayList<Int>
    ) = repository.postRecommendMenuList(recipe, menuType, ingredientList)
}