package com.kaer.menuw.data.model.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostRecipeDto (
    @SerialName("menuName")
    val menuName: String
)