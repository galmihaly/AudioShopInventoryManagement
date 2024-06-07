package com.example.audioshopinventorymanagement.authentication.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

data class ProductListResponse(
    @Required
    @SerializedName("timestamp")
    var timeStamp: String? = "",

    @Required
    @SerializedName("status_code")
    var statusCode: Int? = 0,

    @Required
    @SerializedName("message_type")
    var messageType: String? = "",

    @Required
    @SerializedName("message_body")
    var messageBody: String? = "",

    @Required
    @SerializedName("products")
    var productDetails: List<ProductDetails>? = ArrayList()
)
