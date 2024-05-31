package com.kaer.menuw.domain.usecase

import com.kaer.menuw.domain.repository.MenuRepository
import javax.inject.Inject

class GetLikeMenuListUseCase @Inject constructor(private val repository: MenuRepository) {

    suspend operator fun invoke() = repository.getLikeMenuList()
}