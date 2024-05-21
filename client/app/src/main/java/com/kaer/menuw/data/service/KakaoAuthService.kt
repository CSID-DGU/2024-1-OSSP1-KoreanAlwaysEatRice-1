package com.kaer.menuw.data.service

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class KakaoAuthService @Inject constructor(@ActivityContext private val context: Context) {

    fun startKakaoLogIn(callBack: (OAuthToken?, Throwable?) -> Unit) {
        val isHaveKAKAOAPP = if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            KAKAO_TALK_LOGIN
        } else {
            KAKAO_ACCOUNT_LOGIN
        }

        when (isHaveKAKAOAPP) {
            KAKAO_TALK_LOGIN -> {
                UserApiClient.instance.loginWithKakaoTalk(context, callback = callBack)
            }
            KAKAO_ACCOUNT_LOGIN -> {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callBack)
            }
        }
    }

    companion object {
        private const val KAKAO_TALK_LOGIN = 0
        private const val KAKAO_ACCOUNT_LOGIN = 1
    }
}