package com.kaer.menuw.presentation.onboarding.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.util.KakaoLogInCallback
import com.kakao.sdk.auth.model.OAuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class LogInViewModel : ViewModel() {

    private val _isKakaoLogInSuccess = MutableLiveData<Boolean>()
    val isKakaoLogInSuccess: LiveData<Boolean>
        get() = _isKakaoLogInSuccess

    val kakaoLogInCallback: (OAuthToken?, Throwable?) -> Unit = { oAuthToken, error ->
        KakaoLogInCallback {
            viewModelScope.launch {
                oAuthToken.let {
//                    _isKakaoLogInSuccess.value =
                }
            }
        }.setKakaoCallback(oAuthToken, error)
    }
}