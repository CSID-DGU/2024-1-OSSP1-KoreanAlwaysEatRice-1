package com.kaer.menuw.domain.repository

import com.kaer.menuw.domain.entity.Token

interface TokenRepository {

    suspend fun setToken(accessToken: String, refreshToken: String)
    suspend fun getToken(): Token
}