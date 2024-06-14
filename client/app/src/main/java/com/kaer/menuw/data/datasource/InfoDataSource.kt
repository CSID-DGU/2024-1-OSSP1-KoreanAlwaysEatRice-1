package com.kaer.menuw.data.datasource

import com.kaer.menuw.data.model.remote.response.ResponseGetUserInfoDto

interface InfoDataSource {

    suspend fun getUserInfo(contentType: String, authorization: String): ResponseGetUserInfoDto
}