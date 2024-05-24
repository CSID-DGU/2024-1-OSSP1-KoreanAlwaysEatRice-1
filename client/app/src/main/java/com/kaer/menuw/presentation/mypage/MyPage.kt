package com.kaer.menuw.presentation.mypage

import android.os.Bundle
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityMypageLayoutBinding
import com.kaer.menuw.util.base.BaseActivity

class MyPage : BaseActivity<ActivityMypageLayoutBinding>(R.layout.activity_mypage_layout) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickBackBtn()
    }

    private fun clickBackBtn() {
        binding.appCompatButton.setOnClickListener {
            finish()
        }
    }
}