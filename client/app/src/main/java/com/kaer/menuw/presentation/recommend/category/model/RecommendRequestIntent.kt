package com.kaer.menuw.presentation.recommend.category.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendRequestIntent (
    val recipe: String,
    val menuType: String,
    val ingredientList: ArrayList<Int>
): Parcelable