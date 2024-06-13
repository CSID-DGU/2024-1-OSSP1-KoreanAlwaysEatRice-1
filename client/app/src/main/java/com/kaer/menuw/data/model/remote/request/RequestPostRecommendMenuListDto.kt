package com.kaer.menuw.data.model.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostRecommendMenuListDto(
    @SerialName("recipe")
    val recipe: String,
    @SerialName("menuType")
    val menuType: String,
    @SerialName("ingredientList")
    val ingredientList: ArrayList<Int>
)