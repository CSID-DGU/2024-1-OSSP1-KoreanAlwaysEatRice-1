package com.kaer.menuw.data.datasource.impl

import com.kaer.menuw.data.datasource.InfoDataSource
import com.kaer.menuw.data.model.remote.response.ResponseGetUserInfoDto
import com.kaer.menuw.data.service.InfoService
import javax.inject.Inject

class InfoDataSourceImpl @Inject constructor(private val apiService: InfoService) : InfoDataSource {

    override suspend fun getUserInfo(
        contentType: String,
        authorization: String
    ): ResponseGetUserInfoDto = apiService.getMyInfo(contentType, authorization)
}