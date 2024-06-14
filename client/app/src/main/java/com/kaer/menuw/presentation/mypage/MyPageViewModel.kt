package com.kaer.menuw.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.domain.entity.UserInfo
import com.kaer.menuw.domain.usecase.GetTokenUseCase
import com.kaer.menuw.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo>
        get() = _userInfo

    fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase(
                "application/json",
                "Bearer ${getTokenUseCase().accessToken}"
            ).onSuccess {
                _userInfo.value = it
            }
        }
    }
}