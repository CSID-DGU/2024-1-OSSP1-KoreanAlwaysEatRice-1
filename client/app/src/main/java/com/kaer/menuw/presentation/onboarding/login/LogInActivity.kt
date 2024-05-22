package com.kaer.menuw.presentation.onboarding.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.kaer.menuw.R
import com.kaer.menuw.data.service.KakaoAuthService
import com.kaer.menuw.databinding.ActivityLoginBinding
import com.kaer.menuw.presentation.home.HomeActivity
import com.kaer.menuw.util.base.BaseActivity
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LogInActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    @Inject
    lateinit var kakaoAuthService: KakaoAuthService

    private val viewModel by viewModels<LogInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UserApiClient.instance.accessTokenInfo {tokenInfo, error ->
            if (error != null) {
                startKakaoLogIn()
                isKakaoLogInSuccess()
            } else if (tokenInfo != null) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

//        startKakaoLogIn()
    }

    private fun  startKakaoLogIn() {
        binding.btnKakaoLogIn.setOnClickListener {
            kakaoAuthService.startKakaoLogIn(viewModel.kakaoLogInCallback)
        }
    }

    private fun isKakaoLogInSuccess() {
        viewModel.isKakaoLogInSuccess.observe(this) {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}