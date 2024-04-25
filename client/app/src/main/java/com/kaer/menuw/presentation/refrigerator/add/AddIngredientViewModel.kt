package com.kaer.menuw.presentation.refrigerator.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaer.menuw.domain.entity.IngredientTotal

class AddIngredientViewModel: ViewModel() {

    private val _mockIngredientList: MutableLiveData<List<IngredientTotal>> = MutableLiveData(
        mutableListOf(
            IngredientTotal(
                "채소",
                arrayListOf(
                    IngredientTotal.IngredientItem(1, "가지", ""),
                    IngredientTotal.IngredientItem(2, "양상추", "")
                )
            ),
            IngredientTotal(
                "유제품",
                arrayListOf(
                    IngredientTotal.IngredientItem(3, "우유", ""),
                    IngredientTotal.IngredientItem(4, "버터", "")
                )
            ),
            IngredientTotal(
                "버섯류",
                arrayListOf(
                    IngredientTotal.IngredientItem(5, "새송이", ""),
                    IngredientTotal.IngredientItem(6, "표고", ""),
                    IngredientTotal.IngredientItem(7, "느타리", "")
                )
            ),
            IngredientTotal(
                "유지류",
                arrayListOf(
                    IngredientTotal.IngredientItem(8, "카놀라유", ""),
                    IngredientTotal.IngredientItem(9, "식용유", "")
                )
            ),
        )
    )

    val mockIngredientList: LiveData<List<IngredientTotal>>
        get() = _mockIngredientList

}