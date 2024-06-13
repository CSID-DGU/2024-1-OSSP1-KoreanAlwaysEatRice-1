package com.kaer.menuw.data.model.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPatchMenuLikeDto (
    @SerialName("menuName")
    val menuName: String,
    @SerialName("menuLike")
    val menuLike: Int
)