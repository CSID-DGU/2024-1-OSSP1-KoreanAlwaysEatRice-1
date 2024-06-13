package com.kaer.menuw.data.service

import com.kaer.menuw.data.model.remote.response.ResponsePostLogInDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecipeDto
import retrofit2.http.Header
import retrofit2.http.POST

interface LogInService {

    @POST("/login")
    suspend fun postLogIn(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") authorization: String
    ): ResponsePostLogInDto
}