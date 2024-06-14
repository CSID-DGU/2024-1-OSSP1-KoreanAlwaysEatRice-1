package com.kaer.menuw.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo (
    val userNickName: String? = null,
    val userImageUrl: String? = null
)