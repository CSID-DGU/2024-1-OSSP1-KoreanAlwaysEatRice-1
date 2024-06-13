package com.kaer.menuw.data.model.remote.response

import com.kaer.menuw.domain.entity.RecommendMenu
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostRecommendMenuListDto(
    val data: List<Data>
) {
    @Serializable
    data class Data(
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

    fun toMenuList() = data.map { data ->
        RecommendMenu(
            menuId = data.menuId,
            menuName = data.menuName,
            menuImgUrl = data.menuImageURL,
            ingredients = data.ingredients,
            cal = data.cal,
            na = data.na,
            similarity = data.similarity,
            recommend = data.recommend
        )
    }
}