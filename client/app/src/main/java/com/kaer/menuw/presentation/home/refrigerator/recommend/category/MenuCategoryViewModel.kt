package com.kaer.menuw.presentation.home.refrigerator.recommend.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuCategoryViewModel : ViewModel() {

    private val _currentCategoryPage = MutableLiveData<Int>(RECIPE_PAGE)
    val currentCategoryPage: LiveData<Int>
        get() = _currentCategoryPage

    fun setCategoryFragmentPage(page: Int) {
        _currentCategoryPage.value = page
    }

    companion object {
        const val RECIPE_PAGE = 0
        const val TYPE_PAGE = 1
        const val RECOMMEND_PAGE = 2
    }
}