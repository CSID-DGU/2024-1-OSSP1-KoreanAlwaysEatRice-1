package com.kaer.menuw.presentation.home.likemenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentLikeMenuListBinding
import com.kaer.menuw.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikeMenuListFragment: BaseFragment<FragmentLikeMenuListBinding>(R.layout.fragment_like_menu_list) {

    private val viewModel by viewModels<LikeMenuListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}