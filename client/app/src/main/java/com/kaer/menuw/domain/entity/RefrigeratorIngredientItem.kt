package com.kaer.menuw.domain.entity

import java.time.LocalDate
import java.util.Date

data class RefrigeratorIngredientItem (
    val ingredientId: Int,
    val ingredientName: String,
    val ingredientImageUrl: String,
    val expiryDate: String
)