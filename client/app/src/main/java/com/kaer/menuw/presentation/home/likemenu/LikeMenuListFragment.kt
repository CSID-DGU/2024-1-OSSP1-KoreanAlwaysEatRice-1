package com.kaer.menuw.presentation.home.likemenu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentLikeMenuListBinding
import com.kaer.menuw.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikeMenuListFragment :
    BaseFragment<FragmentLikeMenuListBinding>(R.layout.fragment_like_menu_list) {

    private val viewModel by viewModels<LikeMenuListViewModel>()

    private var _likeMenuListAdapter: LikeMenuListAdapter? = null
    private val likeMenuListAdapter
        get() = requireNotNull(_likeMenuListAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        initSetAdapter()
        initSetLikeMenuList()
    }

    private fun initSetAdapter() {
        _likeMenuListAdapter = LikeMenuListAdapter()
        binding.rcvLikeMenuList.adapter = likeMenuListAdapter
    }

    private fun initSetLikeMenuList() {
        viewModel.getLikeMenuList()
        viewModel.likeMenuList.observe(viewLifecycleOwner) {
            likeMenuListAdapter.submitList(it)
        }
    }
}