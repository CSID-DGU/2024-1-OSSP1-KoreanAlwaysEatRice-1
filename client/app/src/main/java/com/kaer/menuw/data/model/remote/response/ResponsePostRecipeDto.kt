package com.kaer.menuw.data.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostRecipeDto (
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: RecipeList,
) {
    @Serializable
    data class RecipeList(
        @SerialName("recipeList")
        val recipeList: List<String>,
        @SerialName("recipeImageList")
        val recipeImageList: List<String>
    )
}