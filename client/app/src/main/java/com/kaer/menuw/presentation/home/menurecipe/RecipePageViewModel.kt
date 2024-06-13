package com.kaer.menuw.presentation.home.menurecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.domain.entity.RecipeList
import com.kaer.menuw.domain.entity.RecipeListItem
import com.kaer.menuw.domain.usecase.GetTokenUseCase
import com.kaer.menuw.domain.usecase.PatchMenuLikeUseCase
import com.kaer.menuw.domain.usecase.PostMenuRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipePageViewModel @Inject constructor(
    private val postMenuRecipeUseCase: PostMenuRecipeUseCase,
    private val patchMenuLikeUseCase: PatchMenuLikeUseCase,
    private val getTokenUseCase: GetTokenUseCase
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

    private val _menuName = MutableLiveData<String>()
    val menuName: LiveData<String>
        get() = _menuName

    private val _isMenuLikeValid = MutableLiveData<Boolean>()
    val isMenuLikeValid: LiveData<Boolean>
        get() = _isMenuLikeValid

    fun setProgressPercent(current: Int, total: Int) {
        _progressPercent.value = (current * 100 / total)
    }

    fun postRecipeList(menuName: String) {
        _menuName.value = menuName
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

    fun patchMenuLike(menuLike: Int) {
        viewModelScope.launch {
            _isMenuLikeValid.value = menuName.value?.let {
                patchMenuLikeUseCase("application/json", "Bearer ${getTokenUseCase().accessToken}",
                    it, menuLike).isSuccess
            }
        }
    }

    companion object {
        private const val ORDER_START_INDEX = 3
    }
}