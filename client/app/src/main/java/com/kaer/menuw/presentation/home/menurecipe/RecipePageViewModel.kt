package com.kaer.menuw.presentation.home.menurecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.domain.entity.RecipeList
import com.kaer.menuw.domain.usecase.PostMenuRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipePageViewModel @Inject constructor(
    private val postMenuRecipeUseCase: PostMenuRecipeUseCase
): ViewModel() {

    private val _progressPercent = MutableLiveData<Int>()
    val progressPercent: LiveData<Int>
        get() = _progressPercent

    private val _recipeList = MutableLiveData<RecipeList>()
    val recipeList: LiveData<RecipeList>
        get() = _recipeList

    fun setProgressPercent(current: Int, total: Int) {
        _progressPercent.value = (current*100/total)
    }

    fun postRecipeList(menuName: String) {
        Timber.d("[메뉴 조리법 리스트] -> 테스트")
        viewModelScope.launch {
            postMenuRecipeUseCase(menuName).onSuccess {
                _recipeList.value = it
                Timber.d("[메뉴 조리법 리스트] -> $it")
            }
        }
    }
}