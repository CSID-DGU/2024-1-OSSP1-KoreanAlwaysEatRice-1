package com.kaer.menuw.presentation.refrigerator.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaer.menuw.domain.entity.RecommendMenu

class IngredientRecommendMenuViewModel: ViewModel() {

    private val _mockMenuList: MutableLiveData<List<RecommendMenu>> = MutableLiveData(
        mutableListOf(
            RecommendMenu(
                1,
                "",
                "파전",
                "밀가루, 파, 식용유, ..."
            ),
            RecommendMenu(
                2,
                "",
                "파전",
                "밀가루, 파, 식용유, ..."
            ),
            RecommendMenu(
                3,
                "",
                "파전",
                "밀가루, 파, 식용유, ..."
            ),
            RecommendMenu(
                4,
                "",
                "파전",
                "밀가루, 파, 식용유, ..."
            ),
            RecommendMenu(
                5,
                "",
                "파전",
                "밀가루, 파, 식용유, ..."
            ),
            RecommendMenu(
                6,
                "",
                "파전",
                "밀가루, 파, 식용유, ..."
            ),
            RecommendMenu(
                7,
                "",
                "파전",
                "밀가루, 파, 식용유, ..."
            ),
        )
    )

    val mockMenuList: LiveData<List<RecommendMenu>>
        get() = _mockMenuList
}