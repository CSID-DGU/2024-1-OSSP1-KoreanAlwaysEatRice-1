package com.kaer.menuw.presentation.home.refrigerator.recommend.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentCategoryRecipeBinding
import com.kaer.menuw.util.base.BaseFragment

class CategoryRecipeFragment :
    BaseFragment<FragmentCategoryRecipeBinding>(R.layout.fragment_category_recipe) {

    private val viewModel by activityViewModels<MenuCategoryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickSkipBtn()
    }

    private fun clickSkipBtn() {
        binding.btnCategoryRecipeNext.setOnClickListener {
            viewModel.setCategoryFragmentPage(1)
        }
    }

}