package com.kaer.menuw.domain.repository

import com.kaer.menuw.domain.entity.UserInfo

interface UserRepository {

    suspend fun setUserInfo(userName: String, userImg: String)
    suspend fun getUserInfo(): UserInfo
}