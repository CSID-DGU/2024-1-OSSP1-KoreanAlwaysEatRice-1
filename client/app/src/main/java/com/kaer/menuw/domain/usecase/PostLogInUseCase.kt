package com.kaer.menuw.domain.usecase

import com.kaer.menuw.domain.repository.LogInRepository
import javax.inject.Inject

class PostLogInUseCase @Inject constructor(private val repository: LogInRepository) {

    suspend operator fun invoke(contentType: String, authorization: String) = repository.postLogIn(contentType, authorization)
}