package com.kaer.menuw.presentation.home.refrigerator.recommend

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentMenuNotRecommendBinding
import com.kaer.menuw.domain.entity.RecommendMenu
import com.kaer.menuw.presentation.home.menurecipe.RecipePageActivity
import com.kaer.menuw.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuNotRecommendFragment :
    BaseFragment<FragmentMenuNotRecommendBinding>(R.layout.fragment_menu_not_recommend) {

    private val viewModel by activityViewModels<MenuListViewModel>()

    private var _recommendMenuAdapter: RecommendMenuAdapter? = null
    private val recommendMenuAdapter
        get() = requireNotNull(_recommendMenuAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSetNotRecommendMenuList()
    }

    private fun initSetNotRecommendMenuList() {
        val intent = Intent(requireActivity(), RecipePageActivity::class.java)
        _recommendMenuAdapter = RecommendMenuAdapter().apply {
            setOnItemClickListener(object : RecommendMenuAdapter.OnItemClickListener {
                override fun onItemClick(item: RecommendMenu, position: Int) {
                    intent.putExtra(MenuListViewModel.CHOOSE_MENU, item.menuName)
                    startActivity(intent)
                }
            })
        }
        binding.rcvMenuNotRecommend.adapter = recommendMenuAdapter
        viewModel.mockMenuList.observe(viewLifecycleOwner) {
            recommendMenuAdapter.submitList(it)
        }
    }
}