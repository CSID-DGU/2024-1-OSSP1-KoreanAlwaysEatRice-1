package com.kaer.menuw.presentation.home.refrigerator.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.domain.entity.RefrigeratorIngredientItem
import com.kaer.menuw.domain.usecase.GetIngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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

//    private val _updateStoredIngredientArray: MutableLiveData<ArrayList<IngredientTotal.IngredientItem>> =
//        MutableLiveData()
//    val updateStoredIngredientArray: LiveData<ArrayList<IngredientTotal.IngredientItem>>
//        get() = _updateStoredIngredientArray

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

//    fun updateStoredList(storedList: ArrayList<IngredientTotal.IngredientItem>) {
//        _updateStoredIngredientArray.value = storedList
//    }

    fun setIngredientIdList(selectedArray: ArrayList<RefrigeratorIngredientItem>) {
        val idArray = ArrayList<Int>()
        for (i in 0 until  selectedArray.size) {
            idArray.add(selectedArray[i].ingredientId)
        }
        _selectedIngredientIdArray.value = idArray
    }

    fun changeIngredientToRefrigerator(originalArray: ArrayList<IngredientTotal.IngredientItem>): ArrayList<RefrigeratorIngredientItem> {
        val date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val temp = ArrayList<RefrigeratorIngredientItem>()
        for (i in 0 until  originalArray.size) {
            temp.add(RefrigeratorIngredientItem(originalArray[i].ingredientId, originalArray[i].ingredientName, originalArray[i].ingredientImageUrl, date))
        }
        return temp
    }

    fun changeRefrigeratorToIngredient(originalArray: ArrayList<RefrigeratorIngredientItem>): ArrayList<IngredientTotal.IngredientItem> {
        val temp = ArrayList<IngredientTotal.IngredientItem>()
        for (i in 0 until originalArray.size) {
            temp.add(IngredientTotal.IngredientItem(originalArray[i].ingredientId, originalArray[i].ingredientName, originalArray[i].ingredientImageUrl))
        }
        return temp
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