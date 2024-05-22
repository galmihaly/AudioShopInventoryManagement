package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName

data class LoginAuthResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("timestamp")
    val timeStamp: String,

    @SerializedName("status_code")
    val statusCode: Int,

    @SerializedName("message_type")
    val messageType: String,

    @SerializedName("message_body")
    val messageBody: String,

    @SerializedName("login_user_details")
    val loginUserDetails: LoginUserDetails
)