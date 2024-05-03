package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(

    @SerializedName("user_active")
    val userActive: Boolean,

    @SerializedName("device_active")
    val deviceActive: Boolean
)