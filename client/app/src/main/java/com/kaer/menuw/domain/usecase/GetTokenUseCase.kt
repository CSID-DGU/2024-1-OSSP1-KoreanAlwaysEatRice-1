package com.kaer.menuw.domain.usecase

import com.kaer.menuw.domain.repository.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val repository: TokenRepository) {

    suspend operator fun invoke() = repository.getToken()
}