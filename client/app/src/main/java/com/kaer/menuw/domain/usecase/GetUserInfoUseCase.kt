package com.kaer.menuw.domain.usecase

import com.kaer.menuw.domain.repository.InfoRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val repository: InfoRepository) {

    suspend operator fun invoke(contentType: String, authorization: String) =
        repository.getUserInfo(contentType, authorization)
}