package com.kaer.menuw.data.model.remote.response

import com.kaer.menuw.domain.entity.Ingredient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetIngredientDto (
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<Ingredients>,
) {
    @Serializable
    data class Ingredients (
        @SerialName("ingredientId")
        val ingredientId: Int,
        @SerialName("ingredientName")
        val ingredientName: String,
        @SerialName("ingredientImageURL")
        val ingredientImageURL: String,
        @SerialName("ingredientType")
        val ingredientType: String,
    )

    fun toIngredient() = data.map {  ingredients ->
        Ingredient(
            ingredients.ingredientId,
            ingredients.ingredientName,
            ingredients.ingredientImageURL,
            ingredients.ingredientType
        )
    }
}