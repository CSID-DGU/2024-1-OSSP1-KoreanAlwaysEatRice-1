package com.kaer.menuw.data.datasource.impl

import com.kaer.menuw.data.datasource.LogInDataSource
import com.kaer.menuw.data.model.remote.response.ResponsePostLogInDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecipeDto
import com.kaer.menuw.data.service.LogInService
import javax.inject.Inject

class LogInDataSourceImpl @Inject constructor(private val apiService: LogInService) :
    LogInDataSource {

    override suspend fun postLogIn(
        contentType: String,
        authorization: String
    ): ResponsePostLogInDto = apiService.postLogIn(contentType, authorization)
}