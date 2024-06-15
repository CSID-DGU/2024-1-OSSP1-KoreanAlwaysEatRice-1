package com.kaer.menuw.presentation.recommend.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaer.menuw.R
import com.kaer.menuw.domain.entity.CategoryRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuCategoryViewModel @Inject constructor() : ViewModel() {

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

    val categoryType: List<String> = listOf(
        "밥", "반찬", "국/찌개", "후식"
    )

    private val _selectedCategory = MutableLiveData<String>(RECIPE_NOT_CHOOSE)
    val selectedCategory: LiveData<String>
        get() = _selectedCategory

    private val _selectedType = MutableLiveData<String>(TYPE_NOT_CHOOSE)
    val selectedType: LiveData<String>
        get() = _selectedType

    private val _selectedIngredientId = MutableLiveData<ArrayList<Int>>()
    val selectedIngredientId: LiveData<ArrayList<Int>>
        get() = _selectedIngredientId

    fun setSelectedIngredientIdList(selectedArray: ArrayList<Int>) {
        _selectedIngredientId.value = selectedArray
    }

    fun setCategoryFragmentPage(page: Int) {
        _currentCategoryPage.value = page
    }

    fun setSelectedRecipeName(recipeId: Int) {
        when (recipeId) {
            0 -> _selectedCategory.value = RECIPE_BOIL
            1 -> _selectedCategory.value = RECIPE_STEAM
            2 -> _selectedCategory.value = RECIPE_BAKED
            3 -> _selectedCategory.value = RECIPE_FRY
            4 -> _selectedCategory.value = RECIPE_ROASTED
            5 -> _selectedCategory.value = RECIPE_OTHER
            else -> _selectedCategory.value = RECIPE_NOT_CHOOSE
        }
    }

    fun setSelectedTypeName(typeId: Int) {
        when (typeId) {
            0 -> _selectedType.value = TYPE_RICE
            1 -> _selectedType.value = TYPE_SIDE
            2 -> _selectedType.value = TYPE_SOUP
            3 -> _selectedType.value = TYPE_DESSERT
            else -> _selectedType.value = TYPE_NOT_CHOOSE
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

        const val TYPE_RICE = "밥"
        const val TYPE_SIDE = "반찬"
        const val TYPE_SOUP = "국/찌개"
        const val TYPE_DESSERT = "후식"
        const val TYPE_NOT_CHOOSE = ""
    }
}