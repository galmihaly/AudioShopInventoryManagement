package com.example.audioshopinventorymanagement.jwttokensdatastore

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class JwtTokens(
//    @SerializedName("access_token")
    val accessToken : String,

//    @SerializedName("refresh_token")
    val refreshToken: String
)