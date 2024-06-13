package com.kaer.menuw.domain.repository

interface LogInRepository {

    suspend fun postLogIn(contentType: String, authorization: String): Result<Boolean>
}