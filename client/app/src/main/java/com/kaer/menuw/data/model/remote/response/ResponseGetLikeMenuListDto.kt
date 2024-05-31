package com.kaer.menuw.data.model.remote.response

import android.view.Menu
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetLikeMenuListDto (
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: MenuItem
) {
    @Serializable
    data class MenuItem(
        @SerialName("menuId")
        val menuId: Int,
        @SerialName("menuName")
        val menuName: String,
        @SerialName("menuImageURL")
        val menuImageUrl: String,
        @SerialName("ingredients")
        val ingredients: String
    )
}