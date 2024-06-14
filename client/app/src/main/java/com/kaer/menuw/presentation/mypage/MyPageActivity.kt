package com.kaer.menuw.presentation.mypage

import android.os.Bundle
import androidx.activity.viewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityMypageBinding
import com.kaer.menuw.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageActivity : BaseActivity<ActivityMypageBinding>(R.layout.activity_mypage) {

    private val viewModel by viewModels<MyPageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUserInfo()
        clickBackBtn()
    }

    private fun initUserInfo() {
        viewModel.getUserInfo()
        viewModel.userName.observe(this) {
            binding.tvMypageUserNickname.text = it
        }
    }

    private fun clickBackBtn() {
        binding.btnMypageBack.setOnClickListener {
            finish()
        }
    }
}