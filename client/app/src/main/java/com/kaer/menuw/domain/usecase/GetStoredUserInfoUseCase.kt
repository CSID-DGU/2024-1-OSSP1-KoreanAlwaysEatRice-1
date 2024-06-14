package com.kaer.menuw.domain.usecase

import com.kaer.menuw.domain.repository.StoredUserRepository
import javax.inject.Inject

class GetStoredUserInfoUseCase @Inject constructor(private val repository: StoredUserRepository) {

    suspend operator fun invoke() = repository.getUserInfo()
}