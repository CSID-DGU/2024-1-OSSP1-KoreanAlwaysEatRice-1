package com.kaer.menuw.data.model.remote.response

import com.kaer.menuw.domain.entity.RecipeList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostRecipeDto (
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: RecipeListData,
) {
    @Serializable
    data class RecipeListData(
        @SerialName("recipeList")
        val recipeList: List<String>,
        @SerialName("recipeImageList")
        val recipeImageList: List<String>
    )

    fun toRecipeList() = RecipeList(data.recipeList, data.recipeImageList)
}