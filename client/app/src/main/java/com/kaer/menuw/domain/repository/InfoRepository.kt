package com.kaer.menuw.domain.repository

import com.kaer.menuw.domain.entity.UserInfo

interface InfoRepository {

    suspend fun getUserInfo(contentType: String, authorization: String): Result<Boolean>
}