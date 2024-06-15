package com.kaer.menuw.presentation.home.refrigerator.add

import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.R
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.domain.entity.RefrigeratorIngredientItem
import com.kaer.menuw.domain.usecase.GetIngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class AddIngredientViewModel @Inject constructor(
    private val getIngredientUseCase: GetIngredientUseCase,
) : ViewModel() {

    private val ingredientTypeList = ArrayList<IngredientTotal>()

    private val _ingredientList = MutableLiveData<ArrayList<IngredientTotal>>()
    val ingredientList: LiveData<ArrayList<IngredientTotal>>
        get() = _ingredientList

    private val _backgroundTextVisible: MutableLiveData<Boolean> = MutableLiveData()
    val backgroundTextVisible: LiveData<Boolean>
        get() = _backgroundTextVisible

    private val _selectedTypeId: MutableLiveData<Int> = MutableLiveData(0)
    val selectedTypeId: LiveData<Int>
        get() = _selectedTypeId

    private val _selectedIngredientArray: MutableLiveData<ArrayList<IngredientTotal.IngredientItem>> =
        MutableLiveData()
    val selectedIngredientArray: LiveData<ArrayList<IngredientTotal.IngredientItem>>
        get() = _selectedIngredientArray

    private val _expiryDate: MutableLiveData<ArrayList<String>> = MutableLiveData()
    val expiryDate: LiveData<ArrayList<String>>
        get() = _expiryDate

    private val _refrigeratorIngredientArray: MutableLiveData<ArrayList<RefrigeratorIngredientItem>> = MutableLiveData()
    val refrigeratorIngredientArray: LiveData<ArrayList<RefrigeratorIngredientItem>>
        get() = _refrigeratorIngredientArray

    private val _selectedIngredientIdArray: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    val selectedIngredientIdArray: LiveData<ArrayList<Int>>
        get() = _selectedIngredientIdArray

    private val _addBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val addBtnEnabled: LiveData<Boolean>
        get() = _addBtnEnabled

    private val _deleteBtnVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    val deleteBtnVisible: LiveData<Boolean>
        get() = _deleteBtnVisible

    private val _deleteEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val deleteEnabled: LiveData<Boolean>
        get() = _deleteEnabled

    private val _needNotice: MutableLiveData<Boolean> = MutableLiveData(false)
    val needNotice: LiveData<Boolean>
        get() = _needNotice

    var noticeContent: String = ""

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun setExpiryDate(dates: ArrayList<String>) {
        _expiryDate.value = dates
    }

    fun checkNeedNotice(ingredientList: ArrayList<RefrigeratorIngredientItem>) {
        val expiredArray = ArrayList<String>()
        val nearArray = ArrayList<String>()
        noticeContent = ""

        countDate(expiredArray, nearArray, ingredientList)

        if (expiredArray.size > 0 && nearArray.size > 0) {
            _needNotice.value = true
            noticeContent += "\n[ 유통기한 지난 재료 ]\n" + changeArrayToString(expiredArray) + "\n[ 유통기한 임박한 재료 ]\n" + changeArrayToString(nearArray)
        } else if (expiredArray.size > 0) {
            _needNotice.value = true
            noticeContent += "\n[ 유통기한 지난 재료 ]\n" + changeArrayToString(expiredArray)
        } else if (nearArray.size > 0) {
            _needNotice.value = true
            noticeContent += "\n[ 유통기한 임박한 재료 ]\n" + changeArrayToString(nearArray)
        } else {
            _needNotice.value = false
            noticeContent += "\n유통기한이 지난/임박한 재료가 없습니다"
        }
    }

    private fun countDate(expired: ArrayList<String>, near: ArrayList<String>, ingredients: ArrayList<RefrigeratorIngredientItem>) {
        val localDate = LocalDate.now()

        for (i in 0 until ingredients.size) {
            val expiryDateFormat = LocalDate.parse(
                ingredients[i].expiryDate,
                DateTimeFormatter.ofPattern("yyyy - M - d")
            )

            if (ChronoUnit.DAYS.between(expiryDateFormat, localDate) > 0) {
                expired.add(ingredients[i].ingredientName)
            } else if (ChronoUnit.DAYS.between(localDate, expiryDateFormat) <= 3) {
                near.add(ingredients[i].ingredientName)
            }
        }
    }

    private fun changeArrayToString(array: ArrayList<String>): String {
        var temp = ""
        for (i in 0 until array.size) {
            temp += array[i]
            if (i != array.size - 1) {
                temp += ", "
            }
        }
        return temp
    }

    fun getIngredientList() {
        val vegList = ArrayList<IngredientTotal.IngredientItem>()
        val darList = ArrayList<IngredientTotal.IngredientItem>()
        val graList = ArrayList<IngredientTotal.IngredientItem>()
        val meaList = ArrayList<IngredientTotal.IngredientItem>()
        val fishList = ArrayList<IngredientTotal.IngredientItem>()
        val seaList = ArrayList<IngredientTotal.IngredientItem>()
        viewModelScope.launch {
            getIngredientUseCase().onSuccess { ingredients ->
                for (i in ingredients.indices) {
                    when (ingredients[i].ingredientType) {
                        VEGETABLE -> {vegList.add(IngredientTotal.IngredientItem(ingredients[i].ingredientId, ingredients[i].ingredientName, ingredients[i].ingredientImageURL))}
                        DAIRY_FOOD -> {darList.add(IngredientTotal.IngredientItem(ingredients[i].ingredientId, ingredients[i].ingredientName, ingredients[i].ingredientImageURL))}
                        GRAIN -> {graList.add(IngredientTotal.IngredientItem(ingredients[i].ingredientId, ingredients[i].ingredientName, ingredients[i].ingredientImageURL))}
                        MEAT -> {meaList.add(IngredientTotal.IngredientItem(ingredients[i].ingredientId, ingredients[i].ingredientName, ingredients[i].ingredientImageURL))}
                        FISH -> {fishList.add(IngredientTotal.IngredientItem(ingredients[i].ingredientId, ingredients[i].ingredientName, ingredients[i].ingredientImageURL))}
                        SEASONING -> {seaList.add(IngredientTotal.IngredientItem(ingredients[i].ingredientId, ingredients[i].ingredientName, ingredients[i].ingredientImageURL))}
                    }
                }
                ingredientTypeList.add(IngredientTotal(VEGETABLE, vegList))
                ingredientTypeList.add(IngredientTotal(DAIRY_FOOD, darList))
                ingredientTypeList.add(IngredientTotal(GRAIN, graList))
                ingredientTypeList.add(IngredientTotal(MEAT, meaList))
                ingredientTypeList.add(IngredientTotal(FISH, fishList))
                ingredientTypeList.add(IngredientTotal(SEASONING, seaList))

                _ingredientList.value = ingredientTypeList

                if (ingredientTypeList.size > 0) {
                    _isLoading.value = false
                }
            }
        }
    }

    fun setBackgroundTextVisible(visible: Boolean) {
        Timber.d("비어있나? -> $visible")
        _backgroundTextVisible.value = visible
    }

    fun setDeleteBtnVisible(visible: Boolean) {
        _deleteBtnVisible.value = visible
    }

    fun setDeleteEnabled(enabled: Boolean) {
        _deleteEnabled.value = enabled
    }

    fun clickTypeId(typeId: Int) {
        _selectedTypeId.value = typeId
    }

    fun selectedIngredientList(selectedArray: ArrayList<IngredientTotal.IngredientItem>) {
        _selectedIngredientArray.value = selectedArray
    }

    fun setIngredientIdList(selectedArray: ArrayList<RefrigeratorIngredientItem>) {
        val idArray = ArrayList<Int>()
        for (i in 0 until  selectedArray.size) {
            idArray.add(selectedArray[i].ingredientId)
        }
        _selectedIngredientIdArray.value = idArray
    }

    fun changeIngredientToRefrigerator(date: ArrayList<String>, originalArray: ArrayList<IngredientTotal.IngredientItem>): ArrayList<RefrigeratorIngredientItem> {
        val changeArray = ArrayList<RefrigeratorIngredientItem>()
        for (i in 0 until  originalArray.size) {
            changeArray.add(RefrigeratorIngredientItem(originalArray[i].ingredientId, originalArray[i].ingredientName, originalArray[i].ingredientImageUrl, date[i]))
        }
        return changeArray
    }

    fun changeRefrigeratorToIngredient(originalArray: ArrayList<RefrigeratorIngredientItem>): ArrayList<IngredientTotal.IngredientItem> {
        val temp = ArrayList<IngredientTotal.IngredientItem>()
        for (i in 0 until originalArray.size) {
            temp.add(IngredientTotal.IngredientItem(originalArray[i].ingredientId, originalArray[i].ingredientName, originalArray[i].ingredientImageUrl))
        }
        return temp
    }

    fun getDatesFromRefrigerator(originalArray: ArrayList<RefrigeratorIngredientItem>, currentArray: ArrayList<IngredientTotal.IngredientItem>): ArrayList<String> {
        val originalNameList = ArrayList<String>()
        for (i in 0 until originalArray.size) {
            originalNameList.add(originalArray[i].ingredientName)
        }

        val tempDate = ArrayList<String>()

        for (item in currentArray) {
            val index = originalNameList.indexOfFirst { it.equals(item.ingredientName, ignoreCase = true) }
            if (index != -1) {
                Timber.d("테스트 클릭 viewmodel 같은지 -> ${originalNameList[index]}")
                tempDate.add(originalArray[index].expiryDate)
            }
        }
        Timber.d("테스트 클릭 viewmodel dates -> $tempDate")
        return tempDate
    }

    fun setAddBtnEnabled(enabled: Boolean) {
        _addBtnEnabled.value = enabled
    }

    companion object {
        const val VEGETABLE = "채소"
        const val DAIRY_FOOD = "유제품"
        const val GRAIN = "곡물"
        const val MEAT = "육류"
        const val FISH = "생선"
        const val SEASONING = "조미료"
    }

}