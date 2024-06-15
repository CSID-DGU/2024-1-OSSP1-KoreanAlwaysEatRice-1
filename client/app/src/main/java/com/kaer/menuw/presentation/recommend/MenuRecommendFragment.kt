package com.kaer.menuw.presentation.recommend

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentMenuRecommendBinding
import com.kaer.menuw.domain.entity.RecommendMenu
import com.kaer.menuw.presentation.home.menurecipe.RecipePageActivity
import com.kaer.menuw.presentation.recommend.MenuListViewModel.Companion.CHOOSE_MENU
import com.kaer.menuw.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MenuRecommendFragment :
    BaseFragment<FragmentMenuRecommendBinding>(R.layout.fragment_menu_recommend) {

    private val viewModel by activityViewModels<MenuListViewModel>()

    private var _recommendMenuAdapter: RecommendMenuAdapter? = null
    private val recommendMenuAdapter
        get() = requireNotNull(_recommendMenuAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSetRecommendMenuList()
    }

    private fun initSetRecommendMenuList() {
        val intent = Intent(requireActivity(), RecipePageActivity::class.java)
        _recommendMenuAdapter = RecommendMenuAdapter().apply {
            setOnItemClickListener(object : RecommendMenuAdapter.OnItemClickListener {
                override fun onItemClick(item: RecommendMenu, position: Int) {
                    intent.putExtra(CHOOSE_MENU, item.menuName)
                    startActivity(intent)
                }
            })
        }
        binding.rcvMenuRecommend.adapter = recommendMenuAdapter
        viewModel.recommendMenuList.observe(viewLifecycleOwner) {
            Timber.d("recommendMenuList -> $it")
            recommendMenuAdapter.submitList(it)
        }
    }
}