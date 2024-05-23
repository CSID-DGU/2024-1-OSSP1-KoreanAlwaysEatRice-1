package com.kaer.menuw.presentation.home.refrigerator.recommend.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaer.menuw.R
import com.kaer.menuw.domain.entity.CategoryRecipe
import timber.log.Timber

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

    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String>
        get() = _selectedCategory

    fun setCategoryFragmentPage(page: Int) {
        _currentCategoryPage.value = page
    }

    fun setSelectedRecipeName(recipeId: Int) {
        when (recipeId) {
            0 -> {
//                Timber.d("클릭한 아이템 -> $RECIPE_BOIL")
                _selectedCategory.value = RECIPE_BOIL
            }
            1 -> {
//                Timber.d("클릭한 아이템 -> $RECIPE_STEAM")
                _selectedCategory.value = RECIPE_STEAM
            }
            2 -> {
//                Timber.d("클릭한 아이템 -> $RECIPE_BAKED")
                _selectedCategory.value = RECIPE_BAKED
            }
            3 -> {
//                Timber.d("클릭한 아이템 -> $RECIPE_FRY")
                _selectedCategory.value = RECIPE_FRY
            }
             4-> {
//                 Timber.d("클릭한 아이템 -> $RECIPE_ROASTED")
                 _selectedCategory.value = RECIPE_ROASTED
             }
             5-> {
//                 Timber.d("클릭한 아이템 -> $RECIPE_OTHER")
                 _selectedCategory.value = RECIPE_OTHER
             }
            else -> {
//                Timber.d("클릭한 아이템 -> $RECIPE_NOT_CHOOSE")
                _selectedCategory.value = RECIPE_NOT_CHOOSE
            }
        }
    }

    companion object {
        const val RECIPE_PAGE = 0
        const val TYPE_PAGE = 1
        const val RECOMMEND_PAGE = 2

        const val RECIPE_BOIL = "끓이기"
        const val RECIPE_STEAM = "찌기"
        const val RECIPE_BAKED = "굽기"
        const val RECIPE_FRY = "튀기기"
        const val RECIPE_ROASTED = "볶기"
        const val RECIPE_OTHER = "기타"
        const val RECIPE_NOT_CHOOSE = ""
    }
}