package com.kaer.menuw.data.service

import com.kaer.menuw.data.model.remote.response.ResponseGetUserInfoDto
import retrofit2.http.GET
import retrofit2.http.Header

interface InfoService {

    @GET("/mypage/info")
    suspend fun getMyInfo(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") authorization: String
    ): ResponseGetUserInfoDto
}