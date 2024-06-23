package com.example.audioshopinventorymanagement.api.responses

import com.google.gson.annotations.SerializedName

data class LoginUserDetails(

    @SerializedName("id")
    val id: Int,

    @SerializedName("is_login")
    val isLogin: Boolean,

    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("refresh_token")
    val refreshToken: String,
)