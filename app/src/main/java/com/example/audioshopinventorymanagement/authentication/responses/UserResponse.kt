package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("sayHello")
    val sayHello: String
)