package com.example.audioshopinventorymanagement.jwttokensdatastore

import kotlinx.serialization.Serializable


@Serializable
data class JwtTokens(
    val accessToken : String,
    val refreshJwtTokens: String
)