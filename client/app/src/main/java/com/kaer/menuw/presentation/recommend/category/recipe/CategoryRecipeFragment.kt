package com.kaer.menuw.presentation.recommend.category.recipe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentCategoryRecipeBinding
import com.kaer.menuw.domain.entity.CategoryRecipe
import com.kaer.menuw.presentation.recommend.category.MenuCategoryViewModel
import com.kaer.menuw.presentation.recommend.category.MenuCategoryViewModel.Companion.TYPE_PAGE
import com.kaer.menuw.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                    viewModel.setSelectedRecipeName(position)
                }
            })
        }
        categoryRecipeAdapter.submitList(viewModel.categoryRecipe)
        binding.rcvCategoryRecipe.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = categoryRecipeAdapter
        }
    }

}