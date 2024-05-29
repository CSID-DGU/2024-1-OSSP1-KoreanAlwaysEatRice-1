package com.kaer.menuw.presentation.home.menurecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.domain.entity.RecipeList
import com.kaer.menuw.domain.entity.RecipeListItem
import com.kaer.menuw.domain.usecase.PostMenuRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipePageViewModel @Inject constructor(
    private val postMenuRecipeUseCase: PostMenuRecipeUseCase
) : ViewModel() {

    private val _progressPercent = MutableLiveData<Int>()
    val progressPercent: LiveData<Int>
        get() = _progressPercent

    private val _recipeList = MutableLiveData<RecipeList>()
    val recipeList: LiveData<RecipeList>
        get() = _recipeList

    private val _recipeItemList = MutableLiveData<ArrayList<RecipeListItem>>()
    val recipeItemList: LiveData<ArrayList<RecipeListItem>>
        get() = _recipeItemList

    fun setProgressPercent(current: Int, total: Int) {
        _progressPercent.value = (current * 100 / total)
    }

    fun postRecipeList(menuName: String) {
        viewModelScope.launch {
            postMenuRecipeUseCase(menuName).onSuccess {
                _recipeList.value = it
            }
        }
    }

    fun mapRecipeItemList(recipeList: RecipeList) {
        val tempItemList = ArrayList<RecipeListItem>()
        for (i in 0 until recipeList.recipeList.size) {
            val tempOrder: String =
                (i + 1).toString() + ". " + recipeList.recipeList[i].substring(ORDER_START_INDEX)
            tempItemList.add(RecipeListItem(tempOrder, recipeList.recipeImageList[i]))
        }
        _recipeItemList.value = tempItemList
    }

    companion object {
        private const val ORDER_START_INDEX = 3
    }
}