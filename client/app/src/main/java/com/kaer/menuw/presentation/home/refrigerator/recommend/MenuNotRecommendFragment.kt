package com.kaer.menuw.presentation.home.refrigerator.recommend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentMenuNotRecommendBinding
import com.kaer.menuw.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuNotRecommendFragment: BaseFragment<FragmentMenuNotRecommendBinding>(R.layout.fragment_menu_not_recommend) {

    private val viewModel by activityViewModels<MenuListViewModel>()

    private var _recommendMenuAdapter: RecommendMenuAdapter? = null
    private val recommendMenuAdapter
        get() = requireNotNull(_recommendMenuAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSetNotRecommendMenuList()
    }

    private fun initSetNotRecommendMenuList() {
        _recommendMenuAdapter = RecommendMenuAdapter()
        binding.rcvMenuNotRecommend.adapter = recommendMenuAdapter
        viewModel.mockMenuList.observe(viewLifecycleOwner) {
            recommendMenuAdapter.submitList(it)
        }
    }
}