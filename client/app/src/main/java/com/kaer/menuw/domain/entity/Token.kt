package com.kaer.menuw.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val accessToken: String? = null,
    val refreshToken: String? = null
)