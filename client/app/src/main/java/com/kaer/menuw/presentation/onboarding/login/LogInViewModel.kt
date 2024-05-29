package com.kaer.menuw.presentation.onboarding.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.domain.usecase.PostLogInUseCase
import com.kaer.menuw.util.KakaoLogInCallback
import com.kakao.sdk.auth.model.OAuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val postLogInUseCase: PostLogInUseCase
) : ViewModel() {

    private val _isKakaoLogInSuccess = MutableLiveData<Boolean>()
    val isKakaoLogInSuccess: LiveData<Boolean>
        get() = _isKakaoLogInSuccess

    val kakaoLogInCallback: (OAuthToken?, Throwable?) -> Unit = { oAuthToken, error ->
        KakaoLogInCallback {
            viewModelScope.launch {
                oAuthToken.let {
//                    _isKakaoLogInSuccess.value = true
                    _isKakaoLogInSuccess.value = postLogInUseCase(
                        "application/json",
                        "Bearer ${oAuthToken?.accessToken}"
                    ).isSuccess
                    Timber.d("[카카오 로그인] token -> ${oAuthToken?.accessToken}")
                }
            }
        }.setKakaoCallback(oAuthToken, error)
    }
}