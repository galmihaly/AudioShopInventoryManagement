package com.example.audioshopinventorymanagement.authentication.requests

import com.google.gson.annotations.SerializedName

data class UserLoginRequest(
    @SerializedName("email")
    val email: String,
)
