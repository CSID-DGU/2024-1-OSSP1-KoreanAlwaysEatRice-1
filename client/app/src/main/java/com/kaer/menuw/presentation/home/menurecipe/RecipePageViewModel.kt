package com.kaer.menuw.presentation.home.menurecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipePageViewModel @Inject constructor(): ViewModel() {

    private val _progressPercent = MutableLiveData<Int>()
    val progressPercent: LiveData<Int>
        get() = _progressPercent

    fun setProgressPercent(current: Int, total: Int) {
        _progressPercent.value = (current*100/total)
    }
}