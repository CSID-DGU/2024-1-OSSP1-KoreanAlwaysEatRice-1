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
                    IngredientTotal.IngredientItem(2, "양상추", ""),
                    IngredientTotal.IngredientItem(3, "양상추", ""),
                    IngredientTotal.IngredientItem(4, "양상추", ""),
                    IngredientTotal.IngredientItem(5, "양상추", ""),
                    IngredientTotal.IngredientItem(6, "양상추", ""),
                    IngredientTotal.IngredientItem(7, "양상추", ""),
                    IngredientTotal.IngredientItem(8, "양상추", ""),
                    IngredientTotal.IngredientItem(9, "양상추", ""),
                    IngredientTotal.IngredientItem(10, "양상추", ""),
                    IngredientTotal.IngredientItem(11, "양상추", ""),
                    IngredientTotal.IngredientItem(12, "양상추", ""),
                    IngredientTotal.IngredientItem(13, "양상추", ""),
                )
            ),
            IngredientTotal(
                "유제품",
                arrayListOf(
                    IngredientTotal.IngredientItem(14, "우유", ""),
                    IngredientTotal.IngredientItem(15, "버터", "")
                )
            ),
            IngredientTotal(
                "곡류",
                arrayListOf(
                    IngredientTotal.IngredientItem(16, "쌀", ""),
                    IngredientTotal.IngredientItem(17, "보리", ""),
                    IngredientTotal.IngredientItem(18, "귀리", "")
                )
            ),
            IngredientTotal(
                "육류",
                arrayListOf(
                    IngredientTotal.IngredientItem(19, "카놀라유", ""),
                    IngredientTotal.IngredientItem(20, "식용유", "")
                )
            ),
            IngredientTotal(
                "생선",
                arrayListOf(
                    IngredientTotal.IngredientItem(21, "고등어", ""),
                    IngredientTotal.IngredientItem(22, "연어", "")
                )
            ),
            IngredientTotal(
                "기타",
                arrayListOf(
                    IngredientTotal.IngredientItem(23, "기타", ""),
                    IngredientTotal.IngredientItem(24, "기타", "")
                )
            ),
        )
    )

    val mockIngredientList: LiveData<List<IngredientTotal>>
        get() = _mockIngredientList

    private val _selectedTypeId: MutableLiveData<Int> = MutableLiveData(0)
    val selectedTypeId: LiveData<Int>
        get() = _selectedTypeId

//    private val _selectedIngredientIdArray: MutableLiveData<ArrayList<Int>> = MutableLiveData()
//    val selectedIngredientIdArray: LiveData<ArrayList<Int>>
//        get() = _selectedIngredientIdArray

    private val _selectedIngredientArray: MutableLiveData<ArrayList<IngredientTotal.IngredientItem>> = MutableLiveData()
    val selectedIngredientArray: LiveData<ArrayList<IngredientTotal.IngredientItem>>
        get() = _selectedIngredientArray

    private val _addBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val addBtnEnabled: LiveData<Boolean>
        get() = _addBtnEnabled

    fun clickTypeId(typeId: Int) {
        _selectedTypeId.value = typeId
    }

    fun selectedIngredientList(selectedArray: ArrayList<IngredientTotal.IngredientItem>) {
        _selectedIngredientArray.value = selectedArray
    }

    fun setAddBtnEnabled(enabled: Boolean) {
        _addBtnEnabled.value = enabled
    }

    companion object {
        const val VEGETABLE = "채소"
        const val DAIRY_FOOD = "유제품"
        const val GRAIN = "곡류"
        const val MEAT = "육류"
        const val FISH = "생선"
        const val OTHERS = "기타"
    }

}