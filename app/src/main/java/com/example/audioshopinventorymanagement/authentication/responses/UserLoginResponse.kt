package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class UserLoginResponse(

    @SerializedName("timestamp")
    val timeStamp: String,

    @SerializedName("status")
    val statusCode: Int,

    @SerializedName("message_type")
    val messageType: String,

    @SerializedName("message_body")
    val messageBody: String,

    @SerializedName("user_details")
    val userDetail: UserDetail
)
