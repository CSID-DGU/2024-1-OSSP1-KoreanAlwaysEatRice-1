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
            IngredientTotal(
                "시험",
                arrayListOf(
                    IngredientTotal.IngredientItem(10, "끝났다", ""),
                    IngredientTotal.IngredientItem(11, "행복해", "")
                )
            ),
            IngredientTotal(
                "이제",
                arrayListOf(
                    IngredientTotal.IngredientItem(12, "개발", ""),
                    IngredientTotal.IngredientItem(13, "시작", "")
                )
            ),
            IngredientTotal(
                "더미",
                arrayListOf(
                    IngredientTotal.IngredientItem(14, "데이터", ""),
                    IngredientTotal.IngredientItem(15, "뭐넣지", "")
                )
            ),
            IngredientTotal(
                "생각이",
                arrayListOf(
                    IngredientTotal.IngredientItem(16, "더이상", ""),
                    IngredientTotal.IngredientItem(17, "안나요", "")
                )
            ),
        )
    )

    val mockIngredientList: LiveData<List<IngredientTotal>>
        get() = _mockIngredientList

}