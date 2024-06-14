package com.kaer.menuw.data.repository

import com.kaer.menuw.data.datasource.InfoDataSource
import com.kaer.menuw.domain.repository.InfoRepository
import com.kaer.menuw.domain.repository.StoredUserRepository
import timber.log.Timber
import javax.inject.Inject

class InfoRepositoryImpl @Inject constructor(
    private val infoDataSource: InfoDataSource,
    private val userRepository: StoredUserRepository
) :
    InfoRepository {

    override suspend fun getUserInfo(contentType: String, authorization: String): Result<Boolean> =
        runCatching {
            infoDataSource.getUserInfo(contentType, authorization)
        }.fold(
            onSuccess = {
                userRepository.setUserInfo(it.data.userNickname, it.data.userImageUrl)
                Result.success(true)
            },
            onFailure = {
                Timber.d("내 정보 가져오기 에러 -> ${it.message}")
                Result.failure(it)
            }
        )
}