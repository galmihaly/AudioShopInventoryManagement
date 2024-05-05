package com.example.audioshopinventorymanagement.authentication.requests

import com.google.gson.annotations.SerializedName

data class LoginAuthRequest(

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)
