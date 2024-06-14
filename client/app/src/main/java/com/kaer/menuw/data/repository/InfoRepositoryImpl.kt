package com.kaer.menuw.data.repository

import com.kaer.menuw.data.datasource.InfoDataSource
import com.kaer.menuw.domain.entity.UserInfo
import com.kaer.menuw.domain.repository.InfoRepository
import javax.inject.Inject

class InfoRepositoryImpl @Inject constructor(private val infoDataSource: InfoDataSource) :
    InfoRepository {

    override suspend fun getUserInfo(contentType: String, authorization: String): Result<UserInfo> =
        runCatching {
            infoDataSource.getUserInfo(contentType, authorization).toUserInfo()
        }
}