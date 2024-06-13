package com.kaer.menuw.presentation.home.refrigerator.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.domain.entity.RecommendMenu
import com.kaer.menuw.domain.usecase.PostRecommendMenuListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MenuListViewModel @Inject constructor(
    private val postRecommendMenuListUseCase: PostRecommendMenuListUseCase
) : ViewModel() {

    private val _recommendMenuList = MutableLiveData<List<RecommendMenu>>()
    val recommendMenuList: LiveData<List<RecommendMenu>>
        get() = _recommendMenuList

    private val _notRecommendMenuList = MutableLiveData<List<RecommendMenu>>()
    val notRecommendMenuList: LiveData<List<RecommendMenu>>
        get() = _notRecommendMenuList

    fun postRecommendMenuList(recipe: String, menuType: String, ingredientList: ArrayList<Int>) {
        viewModelScope.launch {
            postRecommendMenuListUseCase(recipe, menuType, ingredientList).onSuccess { list ->
                Timber.d("추천 메뉴 불러오기 success $list")
                val recommendList = ArrayList<RecommendMenu>()
                val notRecommendList = ArrayList<RecommendMenu>()

                for (i in list.indices) {
                    if (list[i].recommend) {
                        recommendList.add(list[i])
                    } else {
                        notRecommendList.add(list[i])
                    }
                }
                _recommendMenuList.value = recommendList
                _notRecommendMenuList.value = notRecommendList
            }.onFailure { Timber.d("추천 메뉴 불러오기 fail ${it.message}") }
        }
    }

    companion object {
        const val CHOOSE_MENU = "CHOOSE_MENU"
    }
}