package com.kaer.menuw.presentation.home.refrigerator.recommend

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentMenuRecommendBinding
import com.kaer.menuw.util.base.BaseFragment

class MenuRecommendFragment: BaseFragment<FragmentMenuRecommendBinding>(R.layout.fragment_menu_recommend) {

    private val viewModel by activityViewModels<MenuListViewModel>()

    private var _recommendMenuAdapter: RecommendMenuAdapter? = null
    private val recommendMenuAdapter
        get() = requireNotNull(_recommendMenuAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSetRecommendMenuList()
    }

    private fun initSetRecommendMenuList() {
        _recommendMenuAdapter = RecommendMenuAdapter()
        binding.rcvMenuRecommend.adapter = recommendMenuAdapter
        viewModel.mockMenuList.observe(viewLifecycleOwner) {
            recommendMenuAdapter.submitList(it)
        }
    }
}