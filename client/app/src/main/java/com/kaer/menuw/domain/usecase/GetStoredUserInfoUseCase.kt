package com.kaer.menuw.domain.usecase

import com.kaer.menuw.domain.repository.UserRepository
import javax.inject.Inject

class GetStoredUserInfoUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke() = repository.getUserInfo()
}