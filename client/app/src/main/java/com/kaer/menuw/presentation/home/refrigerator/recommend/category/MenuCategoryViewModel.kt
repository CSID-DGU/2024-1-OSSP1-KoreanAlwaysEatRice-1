package com.kaer.menuw.presentation.home.refrigerator.recommend.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaer.menuw.R
import com.kaer.menuw.domain.entity.CategoryRecipe

class MenuCategoryViewModel : ViewModel() {

    private val _currentCategoryPage = MutableLiveData<Int>(RECIPE_PAGE)
    val currentCategoryPage: LiveData<Int>
        get() = _currentCategoryPage

    val categoryRecipe: List<CategoryRecipe> = listOf(
        CategoryRecipe(R.drawable.ic_boil, "끓이기"),
        CategoryRecipe(R.drawable.ic_steam, "찌기"),
        CategoryRecipe(R.drawable.ic_baked, "굽기"),
        CategoryRecipe(R.drawable.ic_fry, "튀기기"),
        CategoryRecipe(R.drawable.ic_roast, "볶기"),
        CategoryRecipe(R.drawable.ic_category_other, "기타")
    )

    fun setCategoryFragmentPage(page: Int) {
        _currentCategoryPage.value = page
    }

    companion object {
        const val RECIPE_PAGE = 0
        const val TYPE_PAGE = 1
        const val RECOMMEND_PAGE = 2
    }
}