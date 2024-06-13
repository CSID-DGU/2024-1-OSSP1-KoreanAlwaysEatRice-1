package com.kaer.menuw.domain.entity

data class RecommendMenu (
    val menuId: Int,
    val menuName: String,
    val menuImgUrl: String,
    val ingredients: String,
    val cal: Double,
    val na: Double,
    val similarity: Double,
    val recommend: Boolean
)