package com.kaer.menuw.presentation.mypage

import android.os.Bundle
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityMypageBinding
import com.kaer.menuw.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageActivity : BaseActivity<ActivityMypageBinding>(R.layout.activity_mypage) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickBackBtn()
    }

    private fun clickBackBtn() {
        binding.btnMypageBack.setOnClickListener {
            finish()
        }
    }
}