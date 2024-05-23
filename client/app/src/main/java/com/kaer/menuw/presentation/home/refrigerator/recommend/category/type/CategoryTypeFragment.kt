package com.kaer.menuw.presentation.home.refrigerator.recommend.category.type

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentCategoryTypeBinding
import com.kaer.menuw.presentation.home.refrigerator.recommend.category.MenuCategoryViewModel
import com.kaer.menuw.presentation.home.refrigerator.recommend.category.MenuCategoryViewModel.Companion.RECOMMEND_PAGE
import com.kaer.menuw.util.base.BaseFragment
import timber.log.Timber

class CategoryTypeFragment :
    BaseFragment<FragmentCategoryTypeBinding>(R.layout.fragment_category_type) {

    private val viewModel by activityViewModels<MenuCategoryViewModel>()

    private var _categoryTypeAdapter: CategoryTypeAdapter? = null
    private val categoryTypeAdapter: CategoryTypeAdapter
        get() = requireNotNull(_categoryTypeAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickSkipBtn()
        initSetCategory()
    }

    private fun clickSkipBtn() {
        binding.btnCategoryTypeNext.setOnClickListener {
            viewModel.setCategoryFragmentPage(RECOMMEND_PAGE)
        }
    }

    private fun initSetCategory() {
        _categoryTypeAdapter = CategoryTypeAdapter().apply {
            setOnItemClickListener(object : CategoryTypeAdapter.OnItemClickListener{
                override fun onItemClick(item: String, position: Int) {
                    Timber.d("클릭한 타입 아이템 -> $position")
                }
            })
        }
        categoryTypeAdapter.submitList(viewModel.categoryType)
        binding.rcvCategoryType.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryTypeAdapter
        }
    }
}