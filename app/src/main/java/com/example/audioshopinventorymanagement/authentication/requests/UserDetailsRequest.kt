package com.example.audioshopinventorymanagement.authentication.requests

import com.google.gson.annotations.SerializedName

data class UserDetailsRequest(
    @SerializedName("email")
    val email: String,
)
