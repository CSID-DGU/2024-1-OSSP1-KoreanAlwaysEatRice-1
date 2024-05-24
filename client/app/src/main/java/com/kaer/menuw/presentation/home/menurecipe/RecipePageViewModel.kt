package com.kaer.menuw.presentation.home.menurecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipePageViewModel: ViewModel() {

    private val _progressPercent = MutableLiveData<Int>()
    val progressPercent: LiveData<Int>
        get() = _progressPercent

    fun setProgressPercent(current: Int, total: Int) {
        _progressPercent.value = (current*100/total)
    }
}