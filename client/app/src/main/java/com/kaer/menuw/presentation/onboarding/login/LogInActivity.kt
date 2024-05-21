package com.kaer.menuw.presentation.onboarding.login

import android.os.Bundle
import androidx.activity.viewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityLoginBinding
import com.kaer.menuw.util.base.BaseActivity

class LogInActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val viewModel by viewModels<LogInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKakaoLogIn()
    }

    private fun  startKakaoLogIn() {
        binding.btnKakaoLogIn.setOnClickListener {
            //
        }
    }
}