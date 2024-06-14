package com.kaer.menuw.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.domain.usecase.GetTokenUseCase
import com.kaer.menuw.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase(
                "application/json",
                "Bearer ${getTokenUseCase().accessToken}"
            ).onSuccess {
                _userName.value = it.userNickName
            }
        }
    }
}