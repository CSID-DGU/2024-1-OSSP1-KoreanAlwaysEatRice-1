package com.kaer.menuw.domain.usecase

import com.kaer.menuw.domain.repository.MenuRepository
import javax.inject.Inject

class PostMenuRecipeUseCase @Inject constructor(private val repository: MenuRepository) {

    suspend operator fun invoke(menuName: String) = repository.postMenuRecipe(menuName)
}