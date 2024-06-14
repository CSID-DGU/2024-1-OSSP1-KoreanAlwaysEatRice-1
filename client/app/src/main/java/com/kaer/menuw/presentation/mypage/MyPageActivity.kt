package com.kaer.menuw.presentation.mypage

import android.os.Bundle
import androidx.activity.viewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityMypageBinding
import com.kaer.menuw.util.base.BaseActivity
import com.kaer.menuw.util.base.BindingAdapter.setImage
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
        viewModel.userInfo.observe(this) {
            binding.tvMypageUserNickname.text = it.userNickName
        }
    }

    private fun clickBackBtn() {
        binding.btnMypageBack.setOnClickListener {
            finish()
        }
    }
}