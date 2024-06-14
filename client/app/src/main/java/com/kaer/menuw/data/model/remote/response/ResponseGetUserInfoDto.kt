package com.kaer.menuw.data.model.remote.response

import com.kaer.menuw.domain.entity.UserInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetUserInfoDto(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: Info
) {
    @Serializable
    data class Info(
        @SerialName("userName")
        val userName: String,
        @SerialName("userNickname")
        val userNickname: String,
        @SerialName("userImageUrl")
        val userImageUrl: String
    )

    fun toUserInfo(): UserInfo =
        UserInfo(userNickName = data.userNickname, userImageUrl = data.userImageUrl)
}