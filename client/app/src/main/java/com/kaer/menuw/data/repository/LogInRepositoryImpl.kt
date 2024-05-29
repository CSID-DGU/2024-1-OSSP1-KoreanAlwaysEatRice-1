package com.kaer.menuw.data.repository

import com.kaer.menuw.data.datasource.LogInDataSource
import com.kaer.menuw.domain.entity.Token
import com.kaer.menuw.domain.repository.LogInRepository
import timber.log.Timber
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(
    private val logInDataSource: LogInDataSource,
) : LogInRepository {

    override suspend fun postLogIn(contentType: String, authorization: String): Result<Boolean> =
        runCatching {
            logInDataSource.postLogIn(contentType, authorization)
        }.fold(
            onSuccess = {
                Token(it.data.accessToken, it.data.refreshToken)
                Result.success(true)
            },
            onFailure = {
                Timber.d("[카카오 로그인/토큰 에러] -> ${it.message}")
                Result.failure(it)
            }
        )
}