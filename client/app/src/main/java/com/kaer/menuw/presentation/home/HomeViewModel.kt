package com.kaer.menuw.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.domain.usecase.GetStoredUserInfoUseCase
import com.kaer.menuw.domain.usecase.GetTokenUseCase
import com.kaer.menuw.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getStoredUserInfoUseCase: GetStoredUserInfoUseCase
) : ViewModel() {

    private val _isStoredUserInfoSuccess = MutableLiveData<Boolean>()
    val isStoredUserInfoSuccess: LiveData<Boolean>
        get() = _isStoredUserInfoSuccess

    private val _userName = MutableLiveData<String>()
    val userName: MutableLiveData<String>
        get() = _userName

    fun getUserInfo() {
        viewModelScope.launch {
            _isStoredUserInfoSuccess.value = getUserInfoUseCase(
                "application/json",
                "Bearer ${getTokenUseCase().accessToken}"
            ).isSuccess
            _userName.value = getStoredUserInfoUseCase().userNickName ?: ""
            Timber.d("저장 user info -> ${getStoredUserInfoUseCase().userNickName}")
        }
    }
}