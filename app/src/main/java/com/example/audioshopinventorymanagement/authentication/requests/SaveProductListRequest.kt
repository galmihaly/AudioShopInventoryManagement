package com.example.audioshopinventorymanagement.authentication.requests

import com.google.gson.annotations.SerializedName

data class SaveProductListRequest(

    @SerializedName("username")
    val userName: String?,

    @SerializedName("device_id")
    val deviceId: String?,

    @SerializedName("products")
    val productList: List<SaveProductRequest>?,
)

