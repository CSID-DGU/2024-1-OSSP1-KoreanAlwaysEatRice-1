package com.kaer.menuw.domain.entity

data class IngredientTotal(
    val type: String,
    val ingredientListItem: ArrayList<IngredientItem>
) {
    data class IngredientItem (
        val ingredientId: Int,
        val ingredientName: String,
        val ingredientImageUrl: String
    )
}
