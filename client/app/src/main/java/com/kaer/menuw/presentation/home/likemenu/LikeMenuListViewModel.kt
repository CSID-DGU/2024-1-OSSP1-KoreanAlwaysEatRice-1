package com.kaer.menuw.presentation.home.likemenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.domain.entity.LikeMenu
import com.kaer.menuw.domain.usecase.GetLikeMenuListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LikeMenuListViewModel @Inject constructor(private val getLikeMenuListUseCase: GetLikeMenuListUseCase) :
    ViewModel() {

    private val _likeMenuList = MutableLiveData<List<LikeMenu>>()
    val likeMenuList: LiveData<List<LikeMenu>>
        get() = _likeMenuList

    private val _isListEmpty = MutableLiveData<Boolean>()
    val isListEmpty: LiveData<Boolean>
        get() = _isListEmpty

    fun getLikeMenuList() {
        viewModelScope.launch {
            getLikeMenuListUseCase().onSuccess {
                _likeMenuList.value = it
                _isListEmpty.value = it.isEmpty()
                Timber.d("[좋게 평가한 메뉴 리스트] -> $it")
            }.onFailure { Timber.d("[좋게 평가한 메뉴 리스트] 실패 -> ${it.message}") }
        }
    }
}