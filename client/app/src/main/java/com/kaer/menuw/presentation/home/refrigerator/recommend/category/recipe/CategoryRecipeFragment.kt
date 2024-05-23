package com.kaer.menuw.presentation.home.refrigerator.recommend.category.recipe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentCategoryRecipeBinding
import com.kaer.menuw.domain.entity.CategoryRecipe
import com.kaer.menuw.presentation.home.refrigerator.recommend.category.MenuCategoryViewModel
import com.kaer.menuw.presentation.home.refrigerator.recommend.category.MenuCategoryViewModel.Companion.TYPE_PAGE
import com.kaer.menuw.util.base.BaseFragment
import timber.log.Timber

class CategoryRecipeFragment :
    BaseFragment<FragmentCategoryRecipeBinding>(R.layout.fragment_category_recipe) {

    private val viewModel by activityViewModels<MenuCategoryViewModel>()

    private var _categoryRecipeAdapter: CategoryRecipeAdapter? = null
    private val categoryRecipeAdapter: CategoryRecipeAdapter
        get() = requireNotNull(_categoryRecipeAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickSkipBtn()
        initSetCategory()
    }

    private fun clickSkipBtn() {
        binding.btnCategoryRecipeNext.setOnClickListener {
            viewModel.setCategoryFragmentPage(TYPE_PAGE)
        }
    }

    private fun initSetCategory() {
        _categoryRecipeAdapter = CategoryRecipeAdapter().apply {
            setOnItemClickListener(object : CategoryRecipeAdapter.OnItemClickListener {
                override fun onItemClick(item: CategoryRecipe, position: Int) {
                    Timber.d("클릭한 아이템 -> ${position}")
                }
            })
        }
        categoryRecipeAdapter.submitList(viewModel.categoryRecipe)
        binding.rcvCategoryRecipe.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = categoryRecipeAdapter
        }

//        categoryRecipeAdapter.submitList(viewModel.categoryRecipe)

//        clickCategoryList()
    }

//    private fun clickCategoryList() {
//        categoryRecipeAdapter.setOnRecipeClickListener {
//            Timber.d("선택한 조리법 -> $it")
//        }
//    }

    companion object {
        const val RECIPE_BOIL = "끓이기"
        const val RECIPE_STEAM = "찌기"
        const val RECIPE_BAKED = "굽기"
        const val RECIPE_FRY = "튀기기"
        const val RECIPE_ROASTED = "볶기"
        const val RECIPE_OTHER = "기타"
    }

}