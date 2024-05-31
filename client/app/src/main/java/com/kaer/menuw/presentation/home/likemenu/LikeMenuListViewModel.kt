package com.kaer.menuw.presentation.home.likemenu

import androidx.lifecycle.ViewModel
import com.kaer.menuw.domain.usecase.GetLikeMenuListUseCase
import javax.inject.Inject

class LikeMenuListViewModel @Inject constructor(private val getLikeMenuListUseCase: GetLikeMenuListUseCase) :
    ViewModel() {
}