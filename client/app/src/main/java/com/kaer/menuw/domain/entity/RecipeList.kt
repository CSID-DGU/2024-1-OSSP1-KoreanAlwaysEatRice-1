package com.kaer.menuw.domain.entity

import kotlinx.serialization.SerialName

data class RecipeList(
    val recipeList: List<String>,
    val recipeImageList: List<String>
)