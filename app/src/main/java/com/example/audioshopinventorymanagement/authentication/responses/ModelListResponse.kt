package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class ModelListResponse(

    @Required
    @SerializedName("timestamp")
    val timeStamp: String?,

    @Required
    @SerializedName("status_code")
    val statusCode: Int?,

    @Required
    @SerializedName("message_type")
    val messageType: String?,

    @Required
    @SerializedName("message_body")
    val messageBody: String?,

    @Required
    @SerializedName("models")
    val modelDetails: List<ModelDetails>?
)