package com.kaer.menuw.presentation.home.refrigerator.recommend.category.type

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentCategoryTypeBinding
import com.kaer.menuw.presentation.home.refrigerator.recommend.category.MenuCategoryViewModel
import com.kaer.menuw.presentation.home.refrigerator.recommend.category.MenuCategoryViewModel.Companion.RECOMMEND_PAGE
import com.kaer.menuw.util.base.BaseFragment

class CategoryTypeFragment :
    BaseFragment<FragmentCategoryTypeBinding>(R.layout.fragment_category_type) {

    private val viewModel by activityViewModels<MenuCategoryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickSkipBtn()
    }

    private fun clickSkipBtn() {
//        val intent = Intent(requireActivity(), HomeActivity::class.java)
        binding.btnCategoryTypeNext.setOnClickListener {
//            startActivity(intent)
            viewModel.setCategoryFragmentPage(RECOMMEND_PAGE)
        }
    }
}