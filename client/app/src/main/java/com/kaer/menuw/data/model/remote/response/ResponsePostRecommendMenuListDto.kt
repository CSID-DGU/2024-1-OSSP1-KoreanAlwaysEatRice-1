package com.kaer.menuw.data.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostRecommendMenuListDto(
    @SerialName("menuId")
    val menuId: Int,
    @SerialName("menuName")
    val menuName: String,
    @SerialName("menuImageURL")
    val menuImageURL: String,
    @SerialName("ingredients")
    val ingredients: String,
    @SerialName("recipe")
    val recipe: String,
    @SerialName("recipeImage")
    val recipeImage: String?,
    @SerialName("menuType")
    val menuType: String,
    @SerialName("serving")
    val serving: String,
    @SerialName("cal")
    val cal: Int,
    @SerialName("na")
    val na: Int,
    @SerialName("similarity")
    val similarity: Double,
    @SerialName("recommend")
    val recommend: Boolean,
    @SerialName("user")
    val user: Long?,
    @SerialName("menuLike")
    val menuLike: String?,
)