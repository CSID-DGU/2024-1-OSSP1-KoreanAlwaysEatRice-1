package com.kaer.menuw.domain.usecase

import com.kaer.menuw.domain.repository.MenuRepository
import javax.inject.Inject

class PatchMenuLikeUseCase @Inject constructor(private val repository: MenuRepository) {

    suspend operator fun invoke(
        contentType: String,
        authorization: String,
        menuName: String,
        menuLike: Int
    ) = repository.patchMenuLike(contentType, authorization, menuName, menuLike)
}