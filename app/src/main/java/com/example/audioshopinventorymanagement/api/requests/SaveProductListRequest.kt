package com.example.audioshopinventorymanagement.api.requests

import com.google.gson.annotations.SerializedName

data class SaveProductListRequest(

    @SerializedName("username")
    val userName: String?,

    @SerializedName("device_id")
    val deviceId: String?,

    @SerializedName("products")
    val productList: List<SaveProductRequest>?,
)

