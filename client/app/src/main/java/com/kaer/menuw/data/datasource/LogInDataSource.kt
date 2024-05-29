package com.kaer.menuw.data.datasource

import com.kaer.menuw.data.model.remote.response.ResponsePostLogInDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecipeDto

interface LogInDataSource {

    suspend fun postLogIn(contentType: String, authorization: String): ResponsePostLogInDto
}