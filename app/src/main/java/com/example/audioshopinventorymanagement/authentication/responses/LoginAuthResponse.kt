package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class LoginAuthResponse(

    @SerializedName("timestamp")
    val timeStamp: String,

    @SerializedName("status")
    val statusCode: Int,

    @SerializedName("message_type")
    val messageType: String,

    @SerializedName("message_body")
    val messageBody: String,

    @SerializedName("tokens")
    val loginAuthTokens: LoginAuthTokens
)